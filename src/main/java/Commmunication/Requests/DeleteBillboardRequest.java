package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;

/**
 * Delete a billboard request
 */
public class DeleteBillboardRequest extends Message {
    private String token;
    private String billboard_id;

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
    }
    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = token  + "%%%%" + billboard_id;
        stringToMsg(buffer,payload);
    }
}
