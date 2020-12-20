package BillboardServer.RequestHandler.Handlers;
import BillboardServer.Server;
import BillboardServer.Database.Schema.UserSchema.UserSchema;
import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Requests.LoginRequest;
import Commmunication.Response.LoginResponse;
import Utils.Hash.Hash;
import Utils.Token.Token;

import java.nio.channels.SocketChannel;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Handler Login request from panel
 */
public class LoginRequestHandler extends RequestHandler {
    public LoginRequestHandler(){
    }

    @Override
    public void handle(SocketChannel channel, Message toHandle){
        LoginRequest lgRequest = (LoginRequest) toHandle;
        UserSchema userSchema = new UserSchema(database);
        ArrayList<HashMap<String, String>> rs = userSchema.getUserByName(lgRequest.getUserName());
        LoginResponse lgResponse = new LoginResponse();
        if(rs.size() == 0){
            lgResponse.setMessage("Invalid Credentials");
        }else {
            String userPassword = rs.get(0).get("password");
            String userSalt = rs.get(0).get("salt");
            String inputPassword = lgRequest.getPassWord();
            if(validateUsers(inputPassword, userPassword, userSalt )){
                String payload = rs.get(0).get("user_id") + "%%%%" +
                        rs.get(0).get("username") + "%%%%" +
                        rs.get(0).get("permission");
                String token = Token.encodePayload(payload);
                lgResponse.setMessage(token);
                Server.session.setToken(token);
            }
            else{
                lgResponse.setMessage("Invalid Credentials");
            }
        }
        Message.sendMessage(channel, lgResponse);
    }

    /**
     * Compare the password sent from client with the one in database
     * @param inputPassword Received password
     * @param userPassword Password from database
     * @param userSalt  Salt from database
     * @return True if the credentials is valid
     * @throws NoSuchAlgorithmException The hashing algorithm does not exist
     */
    private static boolean validateUsers(String inputPassword,String userPassword, String userSalt){
        try{
            String hashWithSalt = Hash.hashStringWithSalt(inputPassword, userSalt);
            return hashWithSalt.equals(userPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
