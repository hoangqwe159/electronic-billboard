package BillboardViewer.model;

/**
 * Represent a billboard model
 */
public class Billboard {
    private String userId;
    private String message;
    private String information;
    private String backgroundColor;
    private String messageColor;
    private String picture;
    private String infoColor;
    public String getInfoColor() {
        return infoColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }



    public String getUserId() {
        return userId;
    }

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
}
