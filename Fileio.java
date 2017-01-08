/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save;

import DrawnObjects.*;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ahmad
 */
public class Fileio {

    public void overwrite(String filename, DrawnObjectList dol) throws FileNotFoundException, InvalidIndexException {
        String extfilename = filename + ".txt";
        ArrayList ideas = dol.get_ideas();
        ArrayList lines = dol.get_lines();
        Map<Idea, String> linesid = new HashMap<>();
        PrintWriter writer = new PrintWriter(extfilename);
        ideas.forEach((idea) -> {
            Idea item_to_write = (Idea) idea;
            String id = Integer.toString(idea.hashCode());
            linesid.put(item_to_write, id);
            String x = Integer.toString(item_to_write.get_x());
            String y = Integer.toString(item_to_write.get_y());
            String w = Integer.toString(item_to_write.get_height());
            String h = Integer.toString(item_to_write.get_width());
            String xyhw = x + " " + y + " " + h + " " + w;
            String shape = item_to_write.get_shape();
            String fontstyle = Integer.toString(item_to_write.get_font().getStyle());
            String fontsize = Integer.toString(item_to_write.get_font().getSize());
            String shpfont = shape + " " + fontstyle + " " + fontsize;
            String fontname = item_to_write.get_font().getName();
            String contents = item_to_write.get_contents();
            writer.println(id);
            writer.println(xyhw);
            writer.println(shpfont);
            writer.println(fontname);
            writer.println(contents);
        });
        writer.println("connect");
        lines.forEach((line) -> {
            Line item = (Line) line;
            Idea idea1 = item.get_idea_one();
            Idea idea2 = item.get_idea_two();
            String id1 = linesid.get(idea1);
            String id2 = linesid.get(idea2);
            String link = id1 + " " + id2;
            writer.println(link);
        });
        writer.close();

    }

    public ArrayList open(String filename) throws FileNotFoundException, IOException {
        DrawnObjectList dol = new DrawnObjectList();
        String extfilename = filename;
        if (!filename.endsWith(".txt")) {
            extfilename = filename.concat(".txt");
        }
        FileReader file;
        file = new FileReader(extfilename);
        BufferedReader filelines = new BufferedReader(file);
        String input;
        Map<String, Idea> ideas = new HashMap<>();
        while (!"connect".equals(input = filelines.readLine())) {
            String id = input;
            String[] xyhw = filelines.readLine().split(" ");
            int x = Integer.valueOf(xyhw[0]);
            int y = Integer.valueOf(xyhw[1]);
            int height = Integer.valueOf(xyhw[2]);
            int width = Integer.valueOf(xyhw[3]);
            String[] shpfont = filelines.readLine().split(" ");
            String shape = shpfont[0];
            int fontstyle = Integer.valueOf(shpfont[1]);
            int fontsize = Integer.valueOf(shpfont[2]);
            String fontname = filelines.readLine();
            Font font = new Font(fontname, fontstyle, fontsize);
            String content = filelines.readLine();
            if ("rectangle".equals(shape)) {
                Rectangle newidea = new Rectangle(x, y, content, font, width, height);
                ideas.put(id, newidea);
                dol.add_object(newidea);
            }
            if ("ellipse".equals(shape)) {
                Ellipse newidea2 = new Ellipse(x, y, content, font, width, height);
                ideas.put(id, newidea2);
                dol.add_object(newidea2);
            }
        }
        while ((input = filelines.readLine()) != null) {
            String[] splitline;
            splitline = input.split(" ");
            Idea idea1 = ideas.get(splitline[0]);
            Idea idea2 = ideas.get(splitline[1]);
            Line newline = new Line(idea1, idea2);
            dol.add_object(newline);
        }
        file.close();
        filelines.close();
        return dol.get_all();
    }

}
