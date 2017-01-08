/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrawnObjects;

import java.util.ArrayList;

/**
 * A class to store an ordered list of drawn objects. Will be automatically
 * sorted to put objects of the type Line at the beginning. Can only contain
 * objects of the type Line or Idea.
 *
 * @author Austin Seto
 */
public class DrawnObjectList {

    ArrayList contents = new ArrayList();

    /**
     * Counts how many items are in this list
     *
     * @return how many items are in this list
     */
    public int get_length() {
        return this.contents.size();
    }

    /**
     * Gets the object at the given index in this list
     *
     * @param index the index of the item that is desired
     * @return the item at the given index
     * @throws DrawnObjects.InvalidIndexException if attempting to retrieve from
     * a non-existent index
     */
    public Object get_object(int index) throws InvalidIndexException {
        Object output;
        try {
            output = this.contents.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Attempting to retrieve "
                    + "non-existent object");
        }
        return output;
    }

    /**
     * Returns an ArrayList containing all the elements in this list in order
     *
     * @return the contents of this object list
     */
    public ArrayList get_all() {
        return this.contents;
    }

    /**
     * Obtains an ArrayList that contains the subset of the contents of this
     * DrawnObjectList that is all the lines.
     *
     * @return an ArrayList that contains all the lines in this DrawnObjectList
     * @throws DrawnObjects.InvalidIndexException if list is empty
     */
    public ArrayList get_lines() throws InvalidIndexException {
        ArrayList output = new ArrayList();
        // Figure out where the first idea is (and thus where the last line is)
        int loop_end_point = this.index_of_first_idea();
        for (int c = 0; c < loop_end_point; c++) {
            // Loop through all lines, adding them to output
            Object next_item = this.get_object(c);
            output.add(next_item);
        }
        return output;
    }

    /**
     * Obtains an ArrayList that contains the subset of the contents of this
     * DrawnObjectList that is all of the idea bubbles
     *
     * @return an ArrayList containing all idea bubbles in this DrawnObjectList
     * @throws DrawnObjects.InvalidIndexException if list is empty
     */
    public ArrayList get_ideas() throws InvalidIndexException {
        ArrayList output = new ArrayList();
        // Figure out where the first idea is
        int loop_start_point = this.index_of_first_idea();
        for (int c = loop_start_point; c < this.get_length(); c++) {
            // Loop through all the ideas, adding them to output
            Object next_item = this.get_object(c);
            output.add(next_item);
        }
        return output;
    }

    /**
     * Performs a linear search for the desired object and returns its index if
     * said object was found in the object list. If it was not found, will
     * return a negative number.
     *
     * @param desired_object the object to search for
     * @return the index of the object found
     */
    public int find_object(Object desired_object) {
        int output = this.contents.indexOf(desired_object);
        return output;
    }

    /**
     * Moves the object at the given index up a single layer, as long as that is
     * a valid option.
     *
     * @param index the index of the object to move up
     * @throws DrawnObjects.InvalidIndexException if trying to move non-existent
     * object
     */
    public void move_up(int index) throws InvalidIndexException {
        // Boolean to determine whether the object is moved or not
        boolean move_object = true;
        // Do nothing if the object is at end of the list AKA at top
        if (index >= this.get_length()) {
            move_object = false;
        } else if (this.get_object(index) instanceof Line
                && this.get_object(index + 1) instanceof Idea) {
            // If the object at the given index is of the type Line and the 
            // object above it is of the type Idea, do nothing.
            move_object = false;
        }
        // If it has been determined that the object is to be moved, move it
        if (move_object) {
            this.move_item(index, index + 1);
        }
    }

    /**
     * Moves the object at the given index down a single layer as long as that
     * is a valid option.
     *
     * @param index the index of the object to move down
     * @throws DrawnObjects.InvalidIndexException if trying to move non-existent
     * object
     */
    public void move_down(int index) throws InvalidIndexException {
        // Boolean to determine whether the object is moved or not
        boolean move_object = true;
        // Do nothing if the object is at the beginning of the list
        // AKA at the bottom of the list
        if (index == 0) {
            move_object = false;
        } else if (this.get_object(index) instanceof Idea
                && this.get_object(index - 1) instanceof Line) {
            // If the object at the given index is of the type Idea and the
            // object below it is of the type Line, do nothing
            move_object = false;
        }
        // If it has been determined that the object is to be moved, move it
        if (move_object) {
            this.move_item(index, index - 1);
        }
    }

    /**
     * Moves the object at the given index to the highest layer it can go.
     *
     * @param index the index of the item to move
     * @throws DrawnObjects.InvalidIndexException if trying to move a
     * non-existent item
     */
    public void move_to_top(int index) throws InvalidIndexException {
        if (this.get_object(index) instanceof Idea) {
            // If it is an idea, simply put at end of list AKA at top
            move_item(index, this.get_length() - 1);
        } else if (this.get_object(index) instanceof Line) {
            // Else if it is a line
            // Find the index of the first idea bubble and moves the line to
            // the position immediately preceding
            int new_index = this.index_of_first_idea() - 1;
            move_item(index, new_index);
        }
    }

    /**
     * Moves the object at the given index to the lowest layer it can go.
     *
     * @param index the index of the item to move
     * @throws DrawnObjects.InvalidIndexException if trying to move a
     * non-existent item
     */
    public void move_to_bottom(int index) throws InvalidIndexException {
        if (this.get_object(index) instanceof Line) {
            // If it is a line, simply put it at the beginning of the list
            // AKA at the bottom
            move_item(index, 0);
        } else if (this.get_object(index) instanceof Idea) {
            // Else if it is an idea
            // Find the index of the first idea bubble and move the idea at
            // index to this position, moving all other ideas up one spot
            int new_index = this.index_of_first_idea();
            move_item(index, new_index);
        }
    }

    void move_item(int index_of_item, int new_index) throws InvalidIndexException {
        Object object_to_be_moved = this.get_object(index_of_item);
        this.remove_object(index_of_item);
        this.insert_object(object_to_be_moved, new_index);
    }

    int index_of_first_idea() throws InvalidIndexException {
        int index = 0;
        while (!(this.get_object(index) instanceof Idea)
                && index < this.get_length()) {
            // Loops through every index, stopping when it gets to an Idea or
            // the end
            index += 1;
        }
        return index;
    }

    /**
     * Adds the given idea to the the list. The idea will be placed on the top
     * most layer
     *
     * @param idea the idea to add to the list
     */
    public void add_object(Idea idea) {
        this.contents.add(idea);
    }

    /**
     * Adds the given line to the list. The line will be placed on the bottom
     * most layer
     *
     * @param line the line to add to the list
     */
    public void add_object(Line line) {
        this.contents.add(0, line);
    }

    /**
     * Given the indices of two ideas, will create a Line that connects the
     * two of them and add said line to this DrawnObjectList.
     *
     * @param idea_one_index index of the first idea
     * @param idea_two_index index of the second idea
     * @throws DrawnObjects.InvalidIndexException if attempting to access
     * indices that do not exist
     * @throws DrawnObjects.InvalidDrawnObjectTypeException if one or both
     * input indices do not point towards objects of type Idea
     */
    public void connect_ideas(int idea_one_index, int idea_two_index)
            throws InvalidIndexException, InvalidDrawnObjectTypeException {
        Idea idea_one;
        Idea idea_two;
        try {
            idea_one = (Idea) this.get_object(idea_one_index);
            idea_two = (Idea) this.get_object(idea_two_index);
        } catch (ClassCastException e) {
            throw new InvalidDrawnObjectTypeException("Items are not both "
                    + "ideas of some kind!");
        }
        Line new_line = new Line(idea_one, idea_two);
        this.add_object(new_line);
    }

    private void insert_object(Object object, int index) {
        this.contents.add(index, object);
    }

    /**
     * Removes the object at the given index
     *
     * @param index the index of the object that is to be removed
     * @throws DrawnObjects.InvalidIndexException if trying to remove items from
     * a non-existent index
     */
    public void remove_object(int index) throws InvalidIndexException {
        try {
            this.contents.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Attempting to delete "
                    + "non-existent object");
        }
    }
}
