/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillboardControlPanel.model;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The schedule model
 * @author Asus
 */
public class Schedule implements Serializable, Comparable<Schedule> {
       
    private String message;
    private int scheduleId, billboardId, timeLimit, nextRun;
    private Date startTime, createdAt, updateAt; 
    private ArrayList<Date> startRun, endRun;
    private ArrayList<Date> runTime;

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * Set the time when billboard is displayed
     */
    public void setRunTime() {
        long diffDay = 24 * 60 * 60 * 1000;
        Date currentDate = new Date();
        Date run = this.startTime;        
        ArrayList<Date> list = new ArrayList<Date>();
        
        while(run.getTime() < startTime.getTime() + 7 * diffDay ) {
            list.add(run);
            run = new Date(run.getTime() + nextRun * 60 * 1000);
            
        }
        
        this.runTime = list;
        System.out.println(this.runTime);
        
    }

    public ArrayList<Date> getRunTime() {
        return runTime;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public void setStartTime(String  dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        try {

            Date date = formatter.parse(dateInString);
            this.startTime = date;
            

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
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

    /**
     * Compare to schedule
     * @param o - other schedule to compare
     * @return 0 if this schedule is updated before o schedule, -1 if not
     */
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
