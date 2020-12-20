package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.BillboardSchema.BillboardSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.CreateBillboardRequest;
import Commmunication.Response.CreateBillboardResponse;

import java.nio.channels.SocketChannel;
/**
 * Handler Create billboard request from Panel
 */
public class CreateBillboardRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle) {
        CreateBillboardRequest req = (CreateBillboardRequest) toHandle;
        CreateBillboardResponse res = new CreateBillboardResponse();
        /* Validate Token */

        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }
        else{
            /* Create billboard in the database */
            BillboardSchema billboards = new BillboardSchema(database);
            int rowAffected = billboards.createBillboard(req.getUserId(),
                    req.getMessage(),
                    req.getInformation(),
                    req.getBackgroundColor(),
                    req.getMessageColor(),
                    req.getPicture(),
                    req.getInfoColor());
            if(rowAffected == 0){
                res.setMessage("Something went wrong"); //Something wrong with the database
            }else {
                res.setMessage("Successfully created billboard");
            }
        }
        Message.sendMessage(channel, res);
    }
}
