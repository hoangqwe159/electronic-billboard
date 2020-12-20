package BillboardServer.RequestHandler.Handlers;

import BillboardServer.Database.Schema.UserSchema.UserSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.CreateUserRequest;
import Commmunication.Response.CreateUserResponse;

import java.nio.channels.SocketChannel;
/**
 * Handler create user request from Panel
 */
public class CreateUserRequestHandler extends RequestHandler {
    @Override
    public void handle(SocketChannel channel, Message toHandle) {
        CreateUserRequest req = (CreateUserRequest) toHandle;
        CreateUserResponse res = new CreateUserResponse();
        /* Validate token */
        if(tokenValidator.validateToken(req.getToken()).equals("Session expired")){
            res.setMessage("Session expired");
        }else{
            /* Create User */
            UserSchema userSchema = new UserSchema(database);
            int rowAffected = userSchema.createUser(req.getUsername(), req.getPassword(), req.getPermission());
            if (rowAffected == 0) {
                res.setMessage("Something went wrong");
            }else{
                res.setMessage("Add user successfully");
            }
        }
        Message.sendMessage(channel, res);
    }
}
