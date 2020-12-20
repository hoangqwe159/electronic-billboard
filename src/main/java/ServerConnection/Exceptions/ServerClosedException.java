package ServerConnection.Exceptions;

/**
 * Exception indicating that the server is not ready to communicate
 */
public class ServerClosedException extends Exception {
    public ServerClosedException(String errorMessage){
        super(errorMessage);
    }
}
