package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.UserSchema.UserSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.EditUserNoPassRequest;
import Commmunication.Response.EditUserResponse;

import java.nio.channels.SocketChannel;

/**
 * Handler the edit user without editing password
 */
public class EditUserNoPassHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle){
        EditUserNoPassRequest req = (EditUserNoPassRequest) toHandle;
        EditUserResponse res = new EditUserResponse();
        /* Validate token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else {
            /* Edit the user */
            UserSchema userSchema = new UserSchema(database);
            int rowAffected = userSchema.editUserNoPass(
                    req.getUser_id(),
                    req.getUsername(),
                    req.getPermission()
            );
            if (rowAffected == 0) {
                res.setMessage("Something went wrong");
            } else {
                res.setMessage("Edit user successfully");
            }
        }
        Message.sendMessage(channel,res);
    }
}
