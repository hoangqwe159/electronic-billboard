package BillboardServer.RequestHandler;

import BillboardServer.Database.Database.Database;
import BillboardServer.Database.Database.DatabaseAbs;
import BillboardServer.Server;
import BillboardServer.TokenValidator.TokenValidator;
import Commmunication.Message;

import java.nio.channels.SocketChannel;

/**
 * Handle All Request from other nodes
 */

public abstract class RequestHandler {
    protected DatabaseAbs database;
    protected TokenValidator tokenValidator;
    public RequestHandler(){
        this.database = new Database();
        this.database.connect();
        this.tokenValidator = new TokenValidator(Server.session);
    }
    /**
     * Handle the received request
     * @param channel The socket the sent the request
     * @param toHandle The message to handle
     */
    public abstract void handle(SocketChannel channel, Message toHandle);
}