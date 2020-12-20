package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.BillboardSchema.BillboardSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.EditBillboardRequest;
import Commmunication.Response.EditBillboardResponse;

import java.nio.channels.SocketChannel;
/**
 * Handler Edit billboard request from panel
 */
public class EditBillboardRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        EditBillboardRequest req = (EditBillboardRequest) toHandle;
        EditBillboardResponse res = new EditBillboardResponse();
        String token = req.getToken();
        /* Validate token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
            /* Edit the billboard */
            BillboardSchema billboardSchema = new BillboardSchema(database);
            int rowAffected = billboardSchema.editBillboard(
                    req.getBillboard_id(),
                    req.getMessage(),
                    req.getInformation(),
                    req.getBackgroundColor(),
                    req.getMessageColor(),
                    req.getPicture(),
                    req.getInfoColor()
            );
            if(rowAffected == 0){
                res.setMessage("Something when wrong");
            }else{
                res.setMessage("Edit billboard successfully");
            }
        }
        Message.sendMessage(channel,res);
    }
}
