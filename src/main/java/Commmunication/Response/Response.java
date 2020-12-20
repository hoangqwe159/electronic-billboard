package Commmunication.Response;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Represent a response message from server to nodes
 */
public class Response extends Message {
    protected String responseMessage;

    public String getMessage() {
        return responseMessage;
    }

    public void setMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        responseMessage = payload;
    }
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = responseMessage;
        stringToMsg(buffer, payload);
    }
    public boolean isSuccessful(){
        return (!(
                this.responseMessage.equals("Something went wrong")
                || this.responseMessage.equals("No token found")
                || this.responseMessage.equals("Invalid credentials")
                ||this.responseMessage.equals("Session expired"))
        );
    }
    public boolean sessionExpired() {
        return (this.responseMessage.equals("Session expired"));
    }
}
