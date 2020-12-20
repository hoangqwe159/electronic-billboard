package BillboardServer.ScheduleLogic.Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Represent a Schedule object
 */
public class Schedule implements Serializable, Comparable<Schedule> {
    private int scheduleId, billboardId, timeLimit, nextRun;
    private Date startTime, createdAt, updateAt;
    private ArrayList<Date> startRun, endRun;
    private ArrayList<Date> runTime;

    public Schedule(){};

    public void setRunTime() {
        long diffDay = 24 * 60 * 60 * 1000;
        Date run = this.startTime;
        ArrayList<Date> list = new ArrayList<Date>();
        while(run.getTime() < startTime.getTime() + 7 * diffDay) {
            list.add(run);
            run = new Date(run.getTime() + nextRun * 60 * 1000);
        }
        this.runTime = list;
    }
    public ArrayList<Date> getRunTime() {
        return runTime;
    }



    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getBillboardId() {
        return billboardId;
    }

    public void setBillboardId(int billboardId) {
        this.billboardId = billboardId;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getNextRun() {
        return nextRun;
    }

    public void setNextRun(int nextRun) {
        this.nextRun = nextRun;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public ArrayList<Date> getStartRun() {
        return this.startRun;
    }

    public void setStartRun(ArrayList<Date> startRun) {
        this.startRun = startRun;
    }

    public ArrayList<Date> getEndRun() {
        return this.endRun;
    }

    public void setEndRun(ArrayList<Date> endRun) {
        this.endRun = endRun;
    }
    @Override
    public int compareTo(Schedule o) {
        if (this.updateAt.getTime() -  o.updateAt.getTime() > 0){
            return 1;
        }else if (this.updateAt.getTime() -  o.updateAt.getTime() == 0){
            return 0;
        }
        else{
            return -1;
        }
    }



}