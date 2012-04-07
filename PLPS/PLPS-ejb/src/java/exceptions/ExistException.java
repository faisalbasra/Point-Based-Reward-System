/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

/**
 *
 * @author zen9
 */
public class ExistException extends Exception {

    /**
     * Creates a new instance of <code>ExistException</code> without detail message.
     */
    public ExistException() {
    }


    /**
     * Constructs an instance of <code>ExistException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public ExistException(String msg) {
        super(msg);
    }
}
