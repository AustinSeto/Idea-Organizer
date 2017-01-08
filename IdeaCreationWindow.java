/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DrawnObjects.*;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Austin Seto
 */
public class IdeaCreationWindow extends JFrame implements ActionListener {

    Window idea_display_window;
    String[] shapes = {"ellipse", "rectangle"};

    Shape_Selector shape_option;
    Coordinate_Picker coordinates;
    Content_Input content;

    public IdeaCreationWindow() {
        super("Idea Bubble Creator");
        this.setLayout(new GridLayout(4, 1));
        this.setSize(300, 500);

        this.setResizable(false);

        this.shape_option = new Shape_Selector(shapes);
        this.add(shape_option);

        coordinates = new Coordinate_Picker();
        this.add(coordinates);

        content = new Content_Input();
        this.add(content);

        JButton creation_button = new JButton("Create Idea Bubble");
        creation_button.setActionCommand("create");
        creation_button.addActionListener(this);
        this.add(creation_button);
    }

    public IdeaCreationWindow(Window display_window) {
        this();
        this.shape_option = new Shape_Selector(shapes);
        set_display_window(display_window);
    }

    class Shape_Selector extends JPanel implements ActionListener {

        private JTextField display;
        private JButton cycle_shape;
        private String[] shapes;
        private int shapes_index = 0;
        private String currentShape;

        Shape_Selector(String[] shapes) {
            this.setLayout(new GridLayout(1, 2));
            this.shapes = shapes;
            this.currentShape = this.shapes[0];
            this.display = new JTextField();
            this.display.setEditable(false);
            this.display.setText(currentShape);
            this.cycle_shape = new JButton("Change Shape");
            this.cycle_shape.addActionListener(this);

            this.add(this.cycle_shape);
            this.add(display);
            System.out.println(currentShape);
        }

        String get_shape() {
            System.out.println(currentShape);
            return this.currentShape;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (shapes_index >= shapes.length - 1) {
                shapes_index = 0;
            } else {
                shapes_index += 1;
            }
            this.currentShape = shapes[shapes_index];
            this.display.setText(currentShape);
            System.out.println(currentShape);
        }
    }

    class Coordinate_Picker extends JPanel {

        JTextField x_input;
        JTextField y_input;

        Coordinate_Picker() {
            this.setLayout(new GridLayout(2, 2));

            JTextField x_label = new JTextField("X-Coordinate");
            x_label.setEditable(false);
            this.add(x_label);

            JTextField y_label = new JTextField("Y-Coordinate");
            y_label.setEditable(false);
            this.add(y_label);

            x_input = new JTextField();
            this.add(x_input);
            y_input = new JTextField();
            this.add(y_input);
        }

        int get_x_coordinate() {
            String input = x_input.getText();
            int output;
            try {
                output = Integer.parseInt(input);
            } catch (Exception e) {
                output = Integer.MAX_VALUE;
            }
            return output;
        }

        int get_y_coordinate() {
            String input = y_input.getText();
            int output;
            try {
                output = Integer.parseInt(input);
            } catch (Exception e) {
                output = Integer.MAX_VALUE;
            }
            return output;
        }

        void invalid_coordinates_message() {
            x_input.setText("Please input valid coordinates");
            y_input.setText("Please input valid coordinates");
        }
    }

    class Content_Input extends JPanel {

        JTextField input_box;

        Content_Input() {
            this.setLayout(new GridLayout(2, 1));
            JTextField title = new JTextField("Content");
            title.setEditable(false);
            input_box = new JTextField();

            this.add(title);
            this.add(input_box);
        }

        String content() {
            return input_box.getText();
        }
    }

    public final void set_display_window(Window display_window) {
        this.idea_display_window = display_window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Obtain the coordinates from Coordinate_Picker
        int x = coordinates.get_x_coordinate();
        int y = coordinates.get_y_coordinate();
        boolean x_beyond_bounds = x > idea_display_window.getWidth();
        boolean y_beyond_bounds = y > idea_display_window.getHeight();
        if (x_beyond_bounds || y_beyond_bounds) {
            // Coordinates that would definitely result in the idea bubble
            // being off the screen are rejected
            coordinates.invalid_coordinates_message();
        } else {
            Idea idea_to_insert;
            // Obtain content of idea
            String content_to_insert = content.content();
            // If coordinates are valid, check to see what shape is desired
            String desired_shape = shape_option.get_shape();
            if (desired_shape.equals("rectangle")) {
                idea_to_insert = new Rectangle(x, y, content_to_insert);
            } else {
                // Ellipse is default option
                idea_to_insert = new Ellipse(x, y, content_to_insert);
            }
            idea_display_window.add_object(idea_to_insert);
        }
    }
}
