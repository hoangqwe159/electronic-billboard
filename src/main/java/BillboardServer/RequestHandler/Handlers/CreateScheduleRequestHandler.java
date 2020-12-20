package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.CreateScheduleRequest;
import Commmunication.Response.CreateScheduleResponse;

import java.nio.channels.SocketChannel;
/**
 * Handler create schedule request from Panel
 */
public class CreateScheduleRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        CreateScheduleRequest req = (CreateScheduleRequest) toHandle;
        CreateScheduleResponse res = new CreateScheduleResponse();
        String token = req.getToken();
        /* Validate token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
            /* Create in database */
            ScheduleSchema scheduleSchema = new ScheduleSchema(database);
            int rowAffected = scheduleSchema.createSchedule(req.getBillboard_id(),
                    req.getStartTime(),
                    req.getTimeLimit(),
                    req.getNextRun()
                    );

            if(rowAffected == 0){
                res.setMessage("Something went wrong");
            }else{
                res.setMessage("Create schedule successfully");
            }
        }
        Message.sendMessage(channel, res);
    }
}
