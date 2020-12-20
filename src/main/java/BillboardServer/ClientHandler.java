package BillboardServer;
//import communication.XMLtransmission.XMLReceiver;

import Commmunication.Message;
import BillboardServer.RequestHandler.RequestHandlerFactory;
import BillboardServer.RequestHandler.RequestHandler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * When ever a ServerConnection is established, this class will be created.
 * It handle communication among nodes.
 */
class ClientHandler extends Thread
{
    private SocketChannel socket;
    private ByteBuffer dataBuffer;
    public ClientHandler(SocketChannel socket){
        this.socket = socket;
    }


    @Override
    public void run() {
        try{
            dataBuffer = ByteBuffer.allocate(26214400);
            // it's customary to log out who's just connected.
            System.out.println("Socket opened to " + socket.getRemoteAddress());
            // we read a message from the socket, and ensure it is a time request
            Message msg = Message.nextMessageFromSocket(socket, dataBuffer);
            // Dynamically create request handler based on the type of the request
            RequestHandler rqHandler = RequestHandlerFactory.getMessageHandler(msg.messageType());
            rqHandler.handle(socket, msg);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Cannot connect to the socket");
                System.exit(0);
            }
            System.out.println("Socket closed");
        }

    }
}