package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.BillboardSchema.BillboardSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.GetAllBillboardRequest;
import Commmunication.Response.EndResponse;
import Commmunication.Response.GetAllBillboardResponse;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler Get all billboard request from panel
 * It send back a series of response each of which contains one data entry (avoid buffer overflow)
 */
public class GetAllBillboardRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        GetAllBillboardRequest req = (GetAllBillboardRequest) toHandle;
        EndResponse end = new EndResponse();
        /* Validate Token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            end.setMessage("Session expired");
            Message.sendMessage(channel, end);
        }
        else{
            try{
                /* Get all billboards from the database */
                BillboardSchema billboardSchema = new BillboardSchema(database);
                ArrayList<HashMap<String, String>> rs = billboardSchema.getAllBillboards();
                if(rs.size() > 0){
                    for (HashMap<String, String> billboard:
                         rs) {
                        GetAllBillboardResponse res = new GetAllBillboardResponse();
                        res.setBillboardId(billboard.get("billboard_id"));
                        res.setUserId(billboard.get("user_id"));
                        res.setMessage(billboard.get("message"));
                        res.setInformation(billboard.get("information"));
                        res.setBackgroundColor(billboard.get("background_color"));
                        res.setMessageColor(billboard.get("message_color"));
                        res.setPicture(billboard.get("picture"));
                        res.setCreatedAt(billboard.get("created_at"));
                        res.setUpdatedAt(billboard.get("updated_at"));
                        res.setInfoColor(billboard.get("info_color"));
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
