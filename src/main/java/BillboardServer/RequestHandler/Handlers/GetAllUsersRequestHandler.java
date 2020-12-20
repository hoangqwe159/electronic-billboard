package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.UserSchema.UserSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.GetAllUsersRequest;
import Commmunication.Response.EndResponse;
import Commmunication.Response.GetAllUsersResponse;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler Get all users request from panel
 * It send back a series of response each of which contains one data entry (avoid buffer overflow)
 */
public class GetAllUsersRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        GetAllUsersRequest req = (GetAllUsersRequest) toHandle;
        EndResponse end = new EndResponse();
        /* Validate the token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            end.setMessage("Session expired");
            Message.sendMessage(channel, end);
        }
        else{
            try{
                /* Get all users from the database */
                UserSchema userSchema = new UserSchema(database);
                ArrayList<HashMap<String, String>> rs = userSchema.getAllUsers();
                if(rs.size() > 0){
                    //Continuously send response
                    for (HashMap<String, String> schedule:
                            rs) {
                        GetAllUsersResponse res = new GetAllUsersResponse();
                        res.setUserId(schedule.get("user_id"));
                        res.setUsername(schedule.get("username"));
                        res.setPermission(schedule.get("permission"));
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
                Message.sendMessage(channel,end);
            }
        }
    }
}
