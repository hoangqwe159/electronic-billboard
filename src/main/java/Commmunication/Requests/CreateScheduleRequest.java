package Commmunication.Requests;

import Commmunication.Message;

import java.nio.ByteBuffer;
import java.sql.Timestamp;

/**
 * Represent a create schedule request
 */
public class CreateScheduleRequest extends Message {
    private String token;
    private String billboard_id;
    private String startTime;
    private String timeLimit;
    private String nextRun;

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
        billboard_id = result[1];
        startTime = result[2];
        timeLimit = result[3];
        nextRun = result[4];
    }

    /**
     * Represent all the field of this class by a string
     * @param buffer the byte buffer containing the message.
     */
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = token + "%%%%"
                + billboard_id + "%%%%"
                + startTime + "%%%%"
                + timeLimit + "%%%%"
                + nextRun;
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

    public String getStartTime() {

        return startTime;
    }

    public void setStartTime(String startTime) {

        this.startTime = startTime;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getNextRun() {
        return nextRun;
    }

    public void setNextRun(String nextRun) {
        this.nextRun = nextRun;
    }
}
