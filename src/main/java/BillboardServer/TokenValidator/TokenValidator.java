package BillboardServer.TokenValidator;

import BillboardServer.Session.Session;

import java.util.Date;

/**
 * Represent a session handler
 */
public class TokenValidator {
    private Session session;
    public TokenValidator(Session session){
        this.session = session;
    }
    /**
     * Check if any token existed in the database
     * @return An error message or a token
     */
    public String retrieveToken(){
        /* No token */
        if (this.session.getToken() == null) {
            return "No token found";
        }else{ //Check the expiry
            long now = new Date().getTime();
            if(session.getExpiry().getTime() < now ){
                session.setToken(null);
                return "Session expired";
            }else{
                session.updateExpiry();
                return session.getToken();//Return a token and update expiry time (+ 24hrs)
            }
        }
    }

    /**
     * Validate the expiry time the token
     * @param token Token sent from client
     * @return An error message or a token
     */
    public String validateToken(String token){
        /* No token */
        if(session.getToken() == null){
            return "Session expired";
        }else if(!token.equals(session.getToken())){ //Invalid token
            return "Session expired";
        }else{ //Token expired
            long now = new Date().getTime();
            if(session.getExpiry().getTime() < now){
                session.setToken(null);
                return "Session expired";
            }else{
                session.updateExpiry();
                return session.getToken();//Return a token and update expiry time (+ 24hrs)
            }
        }
    }

}
