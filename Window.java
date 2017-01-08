/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import DrawnObjects.*;
import java.util.ArrayList;

/**
 *
 * @author Rubaiyat Ashna, Jane Wang, Austin Seto
 */
public class Window extends JFrame implements ActionListener {

    static String APP_NAME = "Idea Organizer";
    DrawnObjectList object_list = new DrawnObjectList();

    public Window() {
        super(APP_NAME);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1100, 850);
        this.setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.add(new TopToolBar());
        this.add(contentPane);

    }

    class TopToolBar extends JToolBar {

        public TopToolBar() {
            this.setBounds(0, 0, 1100, 50);
            this.setBackground(Color.GRAY);
            this.setFloatable(false);
            PrintObjectListButton list_button = new PrintObjectListButton();
            list_button.setBounds(1000, 0, 100, 50);
            this.add(list_button);
        }

        class PrintObjectListButton extends JButton {

            PrintObjectListButton() {
                super("Print Object List");
                this.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        print_object_list();
                    }
                });
            }
        }
    }

    class BottomToolBar extends JToolBar {

    }

    /**
     * Adds the given line to the list of drawn objects in this window
     *
     * @param new_line the line to add
     */
    public void add_object(Line new_line) {
        object_list.add_object(new_line);
    }

    private void print_object_list() {
        ArrayList what_to_print = object_list.get_all();
        System.out.println(what_to_print);
    }

    /**
     * Adds the given idea to the list of drawn objects in this window
     *
     * @param new_idea the idea to add
     */
    public void add_object(Idea new_idea) {
        object_list.add_object(new_idea);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
