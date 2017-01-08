/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrawnObjects;

import java.awt.Font;

/**
 * A class that represents a rectangular idea bubble
 *
 * @author Austin Seto
 */
public class Rectangle extends Idea {

    private static final String SHAPE = "rectangle";

    /**
     * Basic constructor
     *
     * @param x the x-coordinate of this rectangular idea bubble
     * @param y the y-coordinate of this rectangular idea bubble
     * @param contents the contents of this rectangular idea bubble
     */
    public Rectangle(int x, int y, String contents) {
        super(x, y, contents, SHAPE);
    }

    /**
     * Constructor with parameters to set saved width and height
     *
     * @param x the x-coordinate of this rectangular idea bubble
     * @param y the y-coordinate of this rectangular idea bubble
     * @param contents the contents of this rectangular idea bubble
     * @param width the width of this rectangular idea bubble
     * @param height the height of this rectangular idea bubble
     */
    public Rectangle(int x, int y, String contents, int width, int height) {
        this(x, y, contents);
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor with parameters to set font used
     *
     * @param x the x-coordinate of this rectangular idea bubble
     * @param y the y-coordinate of this rectangular idea bubble
     * @param contents the contents of this rectangular idea bubble
     * @param font the font used by this rectangular idea bubble
     */
    public Rectangle(int x, int y, String contents, Font font) {
        this(x, y, contents);
        this.font = font;
    }

    /**
     * Constructor with parameters to set font used as well as saved width and
     * height
     *
     * @param x the x-coordinate of this rectangular idea bubble
     * @param y the y-coordinate of this rectangular idea bubble
     * @param contents the contents of this rectangular idea bubble
     * @param font the font used by this rectangular idea bubble
     * @@param width the width of this rectangular idea bubble
     * @param height the height of this rectangular idea bubble
     */
    public Rectangle(int x, int y, String contents, Font font, int width,
            int height) {
        this(x, y, contents, font);
        this.width = width;
        this.height = height;
    }

}
