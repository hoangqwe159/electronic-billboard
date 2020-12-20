package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.UserSchema.UserSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.DeleteUserRequest;
import Commmunication.Response.DeleteUserResponse;

import java.nio.channels.SocketChannel;
/**
 * Handler Delete schedule request
 */
public class DeleteUserRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle) {
        DeleteUserRequest req = (DeleteUserRequest) toHandle;
        DeleteUserResponse res = new DeleteUserResponse();
        /* Validate token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
            /* Delete the user */
            UserSchema userSchema = new UserSchema(database);
            int rowAffected = userSchema.deleteUser(req.getUserId());
            if(rowAffected == 0){
                res.setMessage("Something went wrong");
            }else{
                res.setMessage("Delete user successfully");
            }
        }
        Message.sendMessage(channel,res);
    }
}
