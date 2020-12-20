package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.EditScheduleRequest;
import Commmunication.Response.EditScheduleResponse;

import java.nio.channels.SocketChannel;
/**
 * Handler Edit schedule request from panel
 */
public class EditScheduleRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        EditScheduleRequest req = (EditScheduleRequest) toHandle;
        EditScheduleResponse res = new EditScheduleResponse();
        String token  = req.getToken();
        /* Validate Token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
            /* Edit the schedule */
            ScheduleSchema scheduleSchema = new ScheduleSchema(database);
            int rowAffected = scheduleSchema.editSchedule(
                    req.getSchedule_id(),
                    req.getStartTime(),
                    req.getTimeLimit(),
                    req.getNextRun()
            );
            if (rowAffected == 0) {
                res.setMessage("Something went wrong");
            }else{
                res.setMessage("Edit schedule successfully");
            }
        }
        Message.sendMessage(channel,res);
    }
}
