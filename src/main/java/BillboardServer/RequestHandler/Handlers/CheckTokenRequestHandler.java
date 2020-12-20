package BillboardServer.RequestHandler.Handlers;

import BillboardServer.RequestHandler.RequestHandler;
import Commmunication.Message;
import Commmunication.Response.CheckTokenResponse;

import java.nio.channels.SocketChannel;

/**
 * Handler CheckTokenRequest from Panel
 */
public class CheckTokenRequestHandler extends RequestHandler {

    @Override
    public void handle(SocketChannel channel, Message toHandle) {
        //Validate token from the request
        CheckTokenResponse res = new CheckTokenResponse();
        res.setMessage(tokenValidator.retrieveToken());
        Message.sendMessage(channel, res);
    }
}
