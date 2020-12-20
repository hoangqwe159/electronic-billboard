package BillboardServer.Session;

import java.util.Date;

/**
 * Represent a session established by clients
 */
public class Session {
    private String token;
    private Date expiry;

    public Session() {
        token = null;
        expiry = null;
    }
    public String getToken() {
        return token;
    }

    /**
     * Set the token with the expiry
     * @param token
     */
    public void setToken(String token) {
        /* Null will be removing token */
        if(token == null){
            this.token = null;
            this.expiry = null;
        }else{
            this.token = token;
            this.expiry = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);//Update expiry
        }
    }

    public Date getExpiry() {
        return expiry;
    }

    /**
     * Update token expiry. Will be called in TokenValidator if the token is valid.
     */
    public void updateExpiry(){
        this.expiry = new Date(new Date().getTime() + 24 * 60 * 60 * 1000);
    }
}
