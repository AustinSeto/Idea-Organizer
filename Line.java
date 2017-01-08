/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrawnObjects;

/**
 * A class that represents a drawn line that connects two idea bubbles
 *
 * @author Austin Seto
 */
public class Line {

    Idea idea_one;
    Idea idea_two;

    /**
     * Constructor
     *
     * @param idea_one the first idea that this line connects to
     * @param idea_two the second idea that this line connects to
     */
    public Line(Idea idea_one, Idea idea_two) {
        this.idea_one = idea_one;
        this.idea_two = idea_two;
    }

    /**
     * Finds the centre point of the first idea that this line links to and
     * returns the x-coordinate of said idea's centre point.
     *
     * @return x-coordinate of first idea's centre point
     */
    public int get_point_one_x() {
        int output = this.idea_one.get_centre_x();
        return output;
    }

    /**
     * Finds the centre point of the first idea that this line links to and
     * returns the y-coordinate of said idea's centre point.
     *
     * @return y-coordinate of first idea's centre point
     */
    public int get_point_one_y() {
        int output = this.idea_one.get_centre_y();
        return output;
    }

    /**
     * Finds the centre point of the second idea that this line links to and
     * returns the x-coordinate of said idea's centre point.
     *
     * @return x-coordinate of second idea's centre point
     */
    public int get_point_two_x() {
        int output = this.idea_two.get_centre_x();
        return output;
    }

    /**
     * Finds the centre point of the second idea that this line links to and
     * returns the y-coordinate of said idea's centre point.
     *
     * @return y-coordinate of second idea's centre point
     */
    public int get_point_two_y() {
        int output = this.idea_two.get_centre_y();
        return output;
    }

    /**
     * Getter for the first idea that this line links to
     *
     * @return the first idea that this line links to
     */
    public Idea get_idea_one() {
        return this.idea_one;
    }

    /**
     * Getter for the second idea that this line links to
     *
     * @return the second idea that this line links to
     */
    public Idea get_idea_two() {
        return this.idea_two;
    }
}
