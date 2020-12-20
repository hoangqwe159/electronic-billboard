package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Represent a login request
 */
public class LoginRequest extends Message {
    private String userName;
    private String passWord;

    /**
     * Get the bytes from buffer, convert to string and store them in
     * this class field
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split(" ");
        userName = result[0];
        passWord = result[1];
    }

    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = userName + " " + passWord;
        stringToMsg(buffer, payload);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
