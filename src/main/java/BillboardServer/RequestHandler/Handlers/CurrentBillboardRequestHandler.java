package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.BillboardSchema.BillboardSchema;
import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchema;
import BillboardServer.RequestHandler.RequestHandler;
import BillboardServer.ScheduleLogic.Model.Schedule;
import BillboardServer.ScheduleLogic.ScheduleLogic;
import BillboardServer.ScheduleLogic.ScheduleParser.ScheduleParser;
import Commmunication.Message;
import Commmunication.Response.CurrentBillboardResponse;
import Commmunication.Response.EndResponse;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler CurrentBillboardRequest from Viewer
 */
public class CurrentBillboardRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle) {
        CurrentBillboardResponse res = new CurrentBillboardResponse();
        EndResponse end = new EndResponse();
        /* Get all schedule from the database */
        ScheduleSchema scheduleSchema = new ScheduleSchema(database);
        ArrayList<HashMap<String,String>> rs = scheduleSchema.getAllSchedules();
        if (rs.size() == 0) {
            end.setMessage("0");
        }else{
            /* Parsing all results to an array of Schedule Model */
            ArrayList<Schedule> allSchedules = ScheduleParser.parseResult(rs);
            /* Get the current schedule by user Schedule Logic */
            Schedule currentSchedule = ScheduleLogic.getCurrentSchedule(allSchedules);
            try{
                if(!(currentSchedule == null)){
                    /* Get the billboard id of the current schedule */
                    String currentBillboardId = String.valueOf(currentSchedule.getBillboardId());
                    /* Get that billboard in the data base */
                    BillboardSchema billboardSchema = new BillboardSchema(database);
                    ArrayList<HashMap<String, String>> toShow = billboardSchema.getBillboardById(currentBillboardId);

                    res.setUserId(toShow.get(0).get("user_id"));
                    res.setMessage(toShow.get(0).get("message"));
                    res.setBackgroundColor(toShow.get(0).get("background_color"));
                    res.setMessageColor(toShow.get(0).get("message_color"));
                    res.setInformation(toShow.get(0).get("information"));
                    res.setPicture(toShow.get(0).get("picture"));
                    res.setInfoColor(toShow.get(0).get("info_color"));

                    Message.sendMessage(channel, res); //Send the response with that billboard
                    end.setMessage("1");
                }else{
                    end.setMessage("0");
                }
            }catch (Exception e){
                e.printStackTrace();
                end.setMessage("-1");
            }

        }
        Message.sendMessage(channel,end); //Send an EndResponse with the size of the result
    }
}
