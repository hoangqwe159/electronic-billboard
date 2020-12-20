package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.GetAllScheduleRequest;
import Commmunication.Response.EndResponse;
import Commmunication.Response.GetAllScheduleResponse;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler Get all schedule request from panel
 * It send back a series of response each of which contains one data entry (avoid buffer overflow)
 */
public class GetAllScheduleRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle) {
        GetAllScheduleRequest req = (GetAllScheduleRequest) toHandle;
        EndResponse end = new EndResponse();
        /* Validate Token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            end.setMessage("Session expired");
            Message.sendMessage(channel, end);
        }
        else{
            try{
                /* Get all schedules from the database */
                ScheduleSchema scheduleSchema = new ScheduleSchema(database);
                ArrayList<HashMap<String, String>> rs = scheduleSchema.getAllSchedules();
                if(rs.size() > 0){
                    //Continuously send response
                    for (HashMap<String, String> schedule:
                            rs) {
                        GetAllScheduleResponse res = new GetAllScheduleResponse();
                        res.setScheduleId(schedule.get("schedule_id"));
                        res.setBillboardId(schedule.get("billboard_id"));
                        res.setStartTime(schedule.get("start_time"));
                        res.setTimeLimit(schedule.get("time_limit"));
                        res.setNextRun(schedule.get("next_run"));
                        res.setCreatedAt(schedule.get("created_at"));
                        res.setUpdatedAt(schedule.get("updated_at"));
                        Message.sendMessage(channel, res); //Send a series of responses, each contain one entry of the result
                    }
                    end.setMessage(String.valueOf(rs.size()));
                }
                else{
                    end.setMessage("0");
                }
                Message.sendMessage(channel, end); //Send an end message with the size of the results
            }catch (Exception e){
                end.setMessage("-1"); //Send an end message with an error
                Message.sendMessage(channel, end);
            }
        }
    }
}
