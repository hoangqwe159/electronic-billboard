package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.UserSchema.UserSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.EditUserRequest;
import Commmunication.Response.EditUserResponse;

import java.nio.channels.SocketChannel;

/**
 * Handler Update User request from panel (With editing password)
 */
public class EditUserRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle) {
        EditUserRequest req = (EditUserRequest) toHandle;
        EditUserResponse res = new EditUserResponse();
        /* Validate Token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
                /* Edit the user in the database */
            UserSchema userSchema = new UserSchema(database);
            int rowAffected = userSchema.updateUser(
                    req.getUser_id(),
                    req.getUsername(),
                    req.getPassword(),
                    req.getPermission()
            );
            if(rowAffected == 0){
                res.setMessage("Something went wrong");
            }else{
                res.setMessage("Edit user successfully");
            }
        }
        Message.sendMessage(channel,res);
    }
}
