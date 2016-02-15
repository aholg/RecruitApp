package model;
/**
 * 
 * A exception class purely used for exceptions surround account matters
 */
public class AccException extends Exception {
    private static final long serialVersionUID = 16247164402L;

    public AccException(String msg) {
        super(msg);
    }

}
