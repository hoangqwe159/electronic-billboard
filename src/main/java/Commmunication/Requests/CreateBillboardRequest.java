package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Represent a request to create billboard
 */
public class CreateBillboardRequest extends Message {
    private String token;
    private String user_id;
    private String message;
    private String information;
    private String backgroundColor;
    private String messageColor;
    private String picture;
    private String infoColor;
    /**
     * Get the bytes from buffer, convert to string and store them in
     * this class fields
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split("%%%%");
        token = result[0];
        user_id = result[1];
        backgroundColor = result[2];
        message = result[3];
        messageColor = result[4];
        picture = result[5];
        information = result[6];
        infoColor = result[7];
    }

    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload =token+"%%%%"+
                user_id + "%%%%" +
                backgroundColor + "%%%%" +
                message + "%%%%" +
                messageColor + "%%%%" +
                picture + "%%%%" +
                information + "%%%%" +
                infoColor;
        stringToMsg(buffer, payload);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String username) {
        this.user_id = username;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getMessageColor() {
        return messageColor;
    }

    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
    }

    public String getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }
}
