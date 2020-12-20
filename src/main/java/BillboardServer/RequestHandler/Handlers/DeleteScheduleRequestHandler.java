package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.DeleteScheduleRequest;
import Commmunication.Response.DeleteScheduleResponse;

import java.nio.channels.SocketChannel;

/**
 * Handler Delete schedule request
 */
public class DeleteScheduleRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        DeleteScheduleRequest req = (DeleteScheduleRequest) toHandle;
        DeleteScheduleResponse res = new DeleteScheduleResponse();
        /* Validate token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
            /* Delete the schedule */
            ScheduleSchema scheduleSchema = new ScheduleSchema(database);
            int rowAffected = scheduleSchema.deleteSchedule(req.getSchedule_id());
            if (rowAffected == 0) {
                res.setMessage("Something went wrong");
            }else{
                res.setMessage("Delete schedule successfully");
            }
        }
        Message.sendMessage(channel,res);
    }
}
