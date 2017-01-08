/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrawnObjects;

/**
 * An exception meant for when an invalid index in a DrawnObjectList is
 * accessed. 
 *
 * @author Austin Seto
 */
public class InvalidIndexException extends Exception {

    public InvalidIndexException() {
        super();
    }

    public InvalidIndexException(String message) {
        super(message);
    }
}
