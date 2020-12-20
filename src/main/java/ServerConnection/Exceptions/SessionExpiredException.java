package ServerConnection.Exceptions;

public class SessionExpiredException extends Exception {
    public SessionExpiredException(String errorMessage){
        super(errorMessage);
    }
}
