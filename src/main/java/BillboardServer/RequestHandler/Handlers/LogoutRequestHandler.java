package BillboardServer.RequestHandler.Handlers;

import BillboardServer.RequestHandler.RequestHandler;
import BillboardServer.Server;
import Commmunication.Message;

import java.nio.channels.SocketChannel;
/**
 * Handler Logout request from panel
 */
public class LogoutRequestHandler extends RequestHandler {
    @Override
    /* Delete Token from database */
    public void handle(SocketChannel channel, Message toHandle) {
        Server.session.setToken(null);
    }
}
