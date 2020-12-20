package Commmunication.Response;

import java.nio.ByteBuffer;

/**
 * Represent a get current billboard response
 */
public class CurrentBillboardResponse extends Response {
    private String userId;
    private String message;
    private String information;
    private String backgroundColor;
    private String messageColor;
    private String picture;
    private String infoColor;

    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split("%%%%");
        userId = result[0];
        message = result[1];
        information = result[2];
        backgroundColor = result[3];
        messageColor = result[4];
        picture = result[5];
        infoColor = result[6];
    }
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = userId + "%%%%" +
                message + "%%%%" +
                information + "%%%%" +
                backgroundColor + "%%%%" +
                messageColor + "%%%%" +
                picture + "%%%%" +
                infoColor;
        stringToMsg(buffer, payload);
    }


    public String getUserId() {
        return userId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getInformation() {
        return information;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getMessageColor() {
        return messageColor;
    }

    public String getPicture() {
        return picture;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setMessageColor(String messageColor) {
        this.messageColor = messageColor;
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
