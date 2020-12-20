package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Represent a edit user without password request
 */
public class EditUserNoPassRequest extends Message {
    private String user_id;
    private String username;
    private String permission;
    private String token;
    @Override

    /**
     * Get the bytes from buffer, convert to string and store them in
     * this class field
     * @param buffer the byte buffer containing the message.
     */
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split("%%%%");
        token = result[0];
        user_id = result[1];
        username = result[2];
        permission = result[3];
    }
    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = token + "%%%%" + user_id + "%%%%" + username + "%%%%" + permission;
        stringToMsg(buffer,payload);
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
