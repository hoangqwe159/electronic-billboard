package ServerConnection;

import Commmunication.Message;
import ServerConnection.Exceptions.ServerClosedException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Properties;


/**
 * Every time client want to communicate with server, it has to open a ServerConnection using this class
 */
public class ServerConnection {
    private SocketChannel channel;
    private ByteBuffer byteBuffer;

    public ByteBuffer getByteBuffer() {
        return byteBuffer;
    }

    public SocketChannel getChannel() {
        return channel;
    }

    /**
     * Establish connection to server
     * @throws ServerClosedException - Cannot connect to the server socket
     */
    public ServerConnection() throws ServerClosedException {
        try {
            InputStream input = new FileInputStream("src/main/java/Properties/connection.props");
            Properties conProps = new Properties();
            conProps.load(input);
            String HOSTNAME = conProps.getProperty("con.hostname");
            int PORT = Integer.parseInt(conProps.getProperty("con.port"));
            channel = SocketChannel.open();
            channel.connect(new InetSocketAddress(HOSTNAME, PORT));
            System.out.println("Connected to server: " + channel.getRemoteAddress());
        }
        catch (IOException e) {
            throw new ServerClosedException("Server is currently not running");
        }
    }

    /**
     * Send message among nodes
     * @param msg - Request or response message
     */
    public void sendRequest(Message msg){
        if(this.channel.isOpen()){
            this.byteBuffer = ByteBuffer.allocate(26214400);
            Message.sendMessage(this.channel, msg);
        }
        System.out.println("Request Sent.");
    }
}
