package BillboardServer;

import BillboardServer.Database.Database.Database;
import BillboardServer.Database.Database.DatabaseAbs;
import BillboardServer.Session.Session;

import java.io.*;
import java.net.*;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Properties;

/**
 * Represent a server
 */
public class Server
{
    /**
     * This is the session handler
     */
    public static Session session = new Session();
    public static void main(String[] args){
        try {
            InputStream input = new FileInputStream("src/main/java/Properties/connection.props");
            Properties conProps = new Properties();
            conProps.load(input);
            String HOSTNAME = conProps.getProperty("con.hostname");
            int PORT = Integer.parseInt(conProps.getProperty("con.port"));
            // Server is listening on port 8080
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(HOSTNAME, PORT));
            // Running infinite loop for getting Client request
            DatabaseAbs db = new Database();
            db.connect();
            db.initialize();
            System.out.println("Listenning at port " + PORT);
            while (!Thread.currentThread().isInterrupted())
            {
                // Socket object to receive incoming client requests
                SocketChannel socket = serverSocket.accept();
                System.out.println("A new client is connected : " + socket.getRemoteAddress());
                System.out.println("Assigning new thread for this client");
                // Create a new thread object
                Thread t = new ClientHandler(socket);
                // Invoking the start() method
                t.start();
            }
            serverSocket.close();
        } catch ( Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }
}