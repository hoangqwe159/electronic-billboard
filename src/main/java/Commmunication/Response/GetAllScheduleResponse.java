package Commmunication.Response;

import java.nio.ByteBuffer;

/**
 * Represent a get all schedule response (contains only one entry each response)
 */
public class GetAllScheduleResponse extends Response {
    private String scheduleId;
    private String billboardId;
    private String startTime;
    private String timeLimit;
    private String nextRun;
    private String createdAt;
    private String updatedAt;

    @Override
    public void fromBytes(ByteBuffer buffer) {
        String payload = stringFromMsg(buffer);
        String[] result = payload.split("%%%%");
        scheduleId = result[0];
        billboardId = result[1];
        startTime = result[2];
        timeLimit = result[3];
        nextRun = result[4];
        createdAt = result[5];
        updatedAt = result[6];
    }
    @Override
    public void toBytes(ByteBuffer buffer) {
        String payload = scheduleId + "%%%%" +
                billboardId + "%%%%" +
                startTime + "%%%%" +
                timeLimit + "%%%%" +
                nextRun + "%%%%" +
                createdAt + "%%%%" +
                updatedAt
                ;
        stringToMsg(buffer, payload);
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getBillboardId() {
        return billboardId;
    }

    public void setBillboardId(String billboardId) {
        this.billboardId = billboardId;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
