/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BillboardControlPanel.controller;

import java.io.IOException;
import java.util.*;

import ServerConnection.Exceptions.ServerClosedException;
import Commmunication.Requests.GetAllUsersRequest;
import Commmunication.Response.GetAllUsersResponse;
import ServerConnection.Exceptions.SessionExpiredException;
import ServerConnection.ServerConnection;
import BillboardControlPanel.model.Billboard;
import BillboardControlPanel.model.Schedule;
import BillboardControlPanel.model.User;
import Commmunication.Message;
import Commmunication.Requests.GetAllBillboardRequest;
import Commmunication.Requests.GetAllScheduleRequest;
import Commmunication.Response.EndResponse;
import Commmunication.Response.GetAllBillboardResponse;
import Commmunication.Response.GetAllScheduleResponse;
import Utils.DateFormatter.DateFormatter;

/**
 * Data Access Object to get data from data source
 * @author Asus
 */
public class DAO {
    /**
     * Get list schedules from server
     * @param token - the user's token to send to server
     * @return allUsers - list of all users
     * @throws ServerClosedException - If the server is not running
     */
    public ArrayList<User> getListUser(String token) throws ServerClosedException, SessionExpiredException {
        ArrayList<User> allUsers = new ArrayList<>();
        try{
            //Establish connection to server and send request to server
            ServerConnection svCon = new ServerConnection();
            GetAllUsersRequest rq = new GetAllUsersRequest();
            rq.setToken(token);
            svCon.sendRequest(rq);
            Message msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            //Receive response from server
            while(!(msg.messageType().equals(EndResponse.class.getSimpleName()))){
                GetAllUsersResponse res = (GetAllUsersResponse) msg;
                User oneEntry = new User();
                System.out.println(res.getUserId());
                oneEntry.setUserId(Integer.parseInt(res.getUserId()));
                oneEntry.setUsername(res.getUsername());
                oneEntry.setPermission(new ArrayList<String>(Arrays.asList(res.getPermission().split(","))));
                oneEntry.setCreatedAt(DateFormatter.formatDate(res.getCreatedAt()));
                oneEntry.setUpdateAt(DateFormatter.formatDate(res.getUpdatedAt()));
                allUsers.add(oneEntry);
                msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            }
            EndResponse end = (EndResponse) msg;
            //If error occurs in server, return an empty billboard list
            if (end.getMessage().equals("-1")) {
                allUsers.clear();
                return allUsers;
            }else if (end.getMessage().equals("Session expired")){
                throw new SessionExpiredException("Session expired");
            }
        }catch (IOException | ServerClosedException e){
            throw new ServerClosedException(e.getMessage());
        }

        return allUsers;

    }

    /**
     * Get list billboards from server
     * @param token - the user's token to send to server
     * @return allBillboards - list of all billboards
     * @throws ServerClosedException - If the server is not running
     */
    public  ArrayList<Billboard> getListBillboard(String token) throws ServerClosedException, SessionExpiredException {
        ArrayList<Billboard> allBillboards = new ArrayList<>();
        try{
            //Establish connection to server and send request to server
            ServerConnection svCon = new ServerConnection();
            GetAllBillboardRequest rq = new GetAllBillboardRequest();
            rq.setToken(token);
            svCon.sendRequest(rq);
            Message msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            //Receive response from server
            while(!(msg.messageType().equals(EndResponse.class.getSimpleName()))){
                GetAllBillboardResponse res = (GetAllBillboardResponse) msg;
                Billboard oneEntry = new Billboard();
                oneEntry.setBillboardId(Integer.parseInt(res.getBillboardId()));
                oneEntry.setUserId(Integer.parseInt(res.getUserId()));
                oneEntry.setMessage(res.getMessage());
                oneEntry.setInformation(res.getInformation());
                oneEntry.setBackgroundColor(res.getBackgroundColor());
                oneEntry.setMessageColor(res.getMessageColor());
                oneEntry.setPicture(res.getPicture());
                oneEntry.setCreatedAt(DateFormatter.formatDate(res.getCreatedAt()));
                oneEntry.setUpdateAt(DateFormatter.formatDate(res.getUpdatedAt()));
                oneEntry.setInfoColor(res.getInfoColor());
                allBillboards.add(oneEntry);
                msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            }
            EndResponse end = (EndResponse) msg;
            //If error occurs in server, return an empty billboard list
            if (end.getMessage().equals("-1")) {
                allBillboards.clear();
                return allBillboards;
            }
            else if(end.getMessage().equals("Session expired")){
                throw new SessionExpiredException(end.getMessage());
            }
        }catch (IOException | ServerClosedException e){
            throw new ServerClosedException(e.getMessage());
        }
        return allBillboards;
    }

    /**
     * Get list schedule from server
     * @param token - the user's token to send to server
     * @return allSchedules - list of all schedules
     * @throws ServerClosedException - If the server is not running
     */
    public ArrayList<Schedule> getListSchedule(String token) throws ServerClosedException, SessionExpiredException {
        ArrayList<Schedule> allSchedules = new ArrayList<>();
        try {
            //Establish connection to server and send request to server
            ServerConnection svCon = new ServerConnection();
            GetAllScheduleRequest requestSchedule = new GetAllScheduleRequest();
            requestSchedule.setToken(token);
            svCon.sendRequest(requestSchedule);
            Message msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            while(!(msg.messageType().equals(EndResponse.class.getSimpleName()))){
                //Receive response from server
                GetAllScheduleResponse res = (GetAllScheduleResponse) msg;
                Schedule oneEntry = new Schedule();
                oneEntry.setScheduleId(Integer.parseInt(res.getScheduleId()));
                oneEntry.setBillboardId(Integer.parseInt(res.getBillboardId()));
                oneEntry.setStartTime(DateFormatter.formatDate(res.getStartTime()));
                oneEntry.setTimeLimit(Integer.parseInt(res.getTimeLimit()));
                oneEntry.setNextRun(Integer.parseInt(res.getNextRun()));
                oneEntry.setCreatedAt(DateFormatter.formatDate(res.getCreatedAt()));
                oneEntry.setUpdateAt(DateFormatter.formatDate(res.getUpdatedAt()));
                allSchedules.add(oneEntry);
                msg = Message.nextMessageFromSocket(svCon.getChannel(), svCon.getByteBuffer());
            }
            //If error occurs in server, return an empty schedule list
            EndResponse end = (EndResponse) msg;
            if (end.getMessage().equals("-1")) {
                allSchedules.clear();
                return allSchedules;
            }else if (end.getMessage().equals("Session expired")){
                throw new SessionExpiredException("Session expired");
            }

        } catch (IOException | ServerClosedException e){
            throw new ServerClosedException(e.getMessage());
        }
        return allSchedules;
    }

    /**
     * Sort the schedule list based on updated date and set start run and end run time for schedule
     * @param list - unsorted schedule list
     * @return list - sorted and processed schedule list
     */
    public ArrayList<Schedule> processListSchedule(ArrayList<Schedule> list) {
        if (list.size() > 0) {
            //sort the list based on updated date
            Collections.sort(list);
            //set startRun and endRun based on runTime and timeLimit
            for(Schedule s: list) {
                s.setRunTime();
                s.setStartRun(s.getRunTime());
                ArrayList<Date> endRun = new ArrayList<>();
                //set endRun = startRun + timeLimit
                for (int i = 0; i < s.getStartRun().size(); i++) {
                    endRun.add(new Date(s.getStartRun().get(i).getTime() + s.getTimeLimit() * 60 * 1000));
                }
                s.setEndRun(endRun);
            }



            ArrayList<Date> currentStartRun = new  ArrayList<Date>();
            currentStartRun.addAll(list.get(list.size()-1).getStartRun());

            ArrayList<Date> currentEndRun = new  ArrayList<Date>();
            currentEndRun.addAll(list.get(list.size()-1).getEndRun());


            //set startRun and endRun of every schedules
            //prevent overlapping time and latest-scheduled billboard will take precedence
            for(int i = list.size() - 1; i > 0; i--){
                ArrayList<Date> previousStartRun = list.get(i-1).getStartRun();
                ArrayList<Date> previousEndRun = list.get(i-1).getEndRun();

                for (int j = 0; j < previousStartRun.size(); j++) {
                    for (int k = 0; k < currentStartRun.size(); k++){
                        if (previousStartRun.get(j).getTime() < currentStartRun.get(k).getTime()
                                && previousEndRun.get(j).getTime() > currentStartRun.get(k).getTime()){
                            previousEndRun.set(j, new Date(currentStartRun.get(k).getTime()));
                        }
                        else if (previousStartRun.get(j).getTime() < currentStartRun.get(k).getTime()
                                && previousEndRun.get(j).getTime() >= currentStartRun.get(k).getTime()){
                            previousEndRun.set(j, new Date(currentStartRun.get(k).getTime()));
                        }
                        else if (previousStartRun.get(j).getTime() <= currentEndRun.get(k).getTime()
                                && previousEndRun.get(j).getTime() > currentEndRun.get(k).getTime()) {
                            previousStartRun.set(j, new Date(currentEndRun.get(k).getTime()));

                        } else if (previousStartRun.get(j).getTime() >= currentStartRun.get(k).getTime()
                                && previousEndRun.get(j).getTime() <= currentEndRun.get(k).getTime()) {
                            previousStartRun.set(j, new Date(1L));
                            previousEndRun.set(j, new Date(1L));
                        }

                    }
                }

                for (int j = 0; j < previousStartRun.size(); j++) {
                    if(previousStartRun.get(j).equals(new Date(1L))) {
                        previousStartRun.remove(j);
                        previousEndRun.remove(j);
                    }
                }

                list.get(i-1).setStartRun(previousStartRun);
                list.get(i-1).setEndRun(previousEndRun);

                currentStartRun.addAll(previousStartRun);
                currentEndRun.addAll(previousEndRun);
            }
        }
        return list;
    }

    
}
