package model.exception;

/**
 * Class ControllerExcaption
 * @author Grishkin Andrei
 */
public class ControllerException extends Exception {

    /**
     * Constructor with specified string
     * @param message string
     */
    public ControllerException(String message) {
        super("Controller has a problem, " + message);
    }

    /**
     * Constructor with specified string and exception
     * @param message string
     * @param e error covered
     */
    public ControllerException(String message, Throwable e){
        super("Controller has a problem, " + message, e);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}