/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DrawnObjects;

/**
 * An exception for when an invalid type of DrawnObject is used.
 *
 * @author Austin Seto
 */
public class InvalidDrawnObjectTypeException extends Exception {
    public InvalidDrawnObjectTypeException() {
        super();
    }
    
    public InvalidDrawnObjectTypeException(String message) {
        super(message);
    }
}
