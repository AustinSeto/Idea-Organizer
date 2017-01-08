/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package save;

import DrawnObjects.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Ahmad
 */
public class Save {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, FileNotFoundException, InvalidIndexException {
//        ArrayList d = open("savefile.txt");
//        System.out.println(d);
        Fileio file_interact = new Fileio();
        ArrayList data = file_interact.open("write test.txt.txt");
        System.out.println(data);
        /*DrawnObjectList data = new DrawnObjectList();
        Rectangle idea1 = new Rectangle(10, 20, "swagerrino");
        Ellipse idea2 = new Ellipse(20, 42, "words");
        data.add_object(idea1);
        data.add_object(idea2);
        file_interact.overwrite("write test.txt", data);*/
    }

}
