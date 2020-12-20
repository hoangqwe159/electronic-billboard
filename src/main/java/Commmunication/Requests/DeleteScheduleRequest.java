package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;

public class DeleteScheduleRequest extends Message {
    private String token;
    private String schedule_id;

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
        schedule_id = result[1];
    }
    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = token + "%%%%" + schedule_id;
        stringToMsg(buffer, payload);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }
}
