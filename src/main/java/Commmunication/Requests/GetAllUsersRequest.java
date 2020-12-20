package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Represent a get all user request
 */
public class GetAllUsersRequest extends Message {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Get the bytes from buffer, convert to string and store them in
     * this class field
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        token = payload;
    }

    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = token;
        stringToMsg(buffer, payload);
    }
}
