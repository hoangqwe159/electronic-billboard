package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Represent a edit billboard request
 */
public class EditBillboardRequest extends Message {
    private String token;
    private String billboard_id;
    private String message;
    private String information;
    private String backgroundColor;
    private String messageColor;
    private String picture;
    private String infoColor;

    /**
     * Get the bytes from buffer, convert to string and store them in
     * this class field
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split("%%%%");
        token = result[0];
        billboard_id = result[1];
        message = result[2];
        information = result[3];
        backgroundColor = result[4];
        messageColor = result[5];
        picture = result[6];
        infoColor = result[7];
    }

    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = token + "%%%%" +
                billboard_id + "%%%%" +
                message + "%%%%" +
                information + "%%%%" +
                backgroundColor + "%%%%" +
                messageColor + "%%%%" +
                picture+ "%%%%" +
                infoColor;
        stringToMsg(buffer,payload);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBillboard_id() {
        return billboard_id;
    }

    public void setBillboard_id(String billboard_id) {
        this.billboard_id = billboard_id;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }
}
