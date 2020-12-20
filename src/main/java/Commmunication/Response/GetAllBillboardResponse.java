package Commmunication.Response;

import java.nio.ByteBuffer;

/**
 * Represent a get all billboard response (contains only one entry each response)
 */
public class GetAllBillboardResponse extends Response {
    private String billboardId;
    private String userId;
    private String message;
    private String information;
    private String backgroundColor;
    private String messageColor;
    private String picture;
    private String infoColor;
    private String createdAt;
    private String updatedAt;

    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split("%%%%");
        billboardId = result[0];
        userId = result[1];
        message = result[2];
        information = result[3];
        backgroundColor = result[4];
        messageColor = result[5];
        picture = result[6];
        createdAt = result[7];
        updatedAt = result[8];
        infoColor = result[9];
    }
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = billboardId + "%%%%" +
                userId + "%%%%" +
                message + "%%%%" +
                information + "%%%%" +
                backgroundColor + "%%%%" +
                messageColor + "%%%%" +
                picture + "%%%%" +
                createdAt + "%%%%" +
                updatedAt + "%%%%" +
                infoColor;
        stringToMsg(buffer, payload);
    }

    public String getBillboardId() {
        return billboardId;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setBillboardId(String billboardId) {
        this.billboardId = billboardId;
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

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }
}
