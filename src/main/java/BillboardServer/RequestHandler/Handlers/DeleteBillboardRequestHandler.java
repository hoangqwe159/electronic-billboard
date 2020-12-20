package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.BillboardSchema.BillboardSchema;
import BillboardServer.Database.Schema.ScheduleSchema.ScheduleSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.DeleteBillboardRequest;
import Commmunication.Response.DeleteBillboardResponse;

import java.nio.channels.SocketChannel;

/**
 * Handler Delete billboard Request from Panel
 */
public class DeleteBillboardRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        DeleteBillboardRequest req = (DeleteBillboardRequest) toHandle;
        DeleteBillboardResponse res = new DeleteBillboardResponse();
        /* Validate token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
            BillboardSchema billboardSchema = new BillboardSchema(database);
            ScheduleSchema scheduleSchema = new ScheduleSchema(database);
            /* Delete the schedule associated with that billboard */
            scheduleSchema.deleteScheduleByBillboard(req.getBillboard_id());

            /* Delete the billboard */
            int rowAffected = billboardSchema.deleteBillboard(req.getBillboard_id());
            if(rowAffected == 0 ){
                res.setMessage("Something went wrong");
            }else{
                res.setMessage("Delete billboard successfully");
            }
        }
        Message.sendMessage(channel, res);
    }
}
