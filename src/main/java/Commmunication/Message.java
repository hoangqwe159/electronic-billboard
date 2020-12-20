package Commmunication;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * A protocol for communication between Server and Client.
 */
public abstract class Message {
    private int length;
    /**
     * Convert the bytes in buffer into this object fields.
     * @param buffer the byte buffer containing the message.
     */
    public abstract void fromBytes(ByteBuffer buffer);

    /**
     * Convert this object into bytes in buffer.
     * @param buffer the byte buffer containing the message.
     */
    public abstract void toBytes(ByteBuffer buffer);

    /**
     * Get the type of the message.
     * @return The message type.
     */
    public String messageType() {
        return getClass().getSimpleName();
    }

    /**
     * Convert a string into a message for the buffer
     * @param buffer the byte buffer containing the message.
     * @param str the string to be sent.
     */
    public static void stringToMsg(ByteBuffer buffer, String str){
        byte[] bytes = str.getBytes();
        int len = bytes.length;
        buffer.putLong((long) len);
        buffer.put(bytes);
    }

    /**
     * Convert a message in the buffer into a string
     * @param buffer the byte buffer containing the message.
     * @return the string field
     */
    public static String stringFromMsg (ByteBuffer buffer){
        int len = (int) buffer.getLong();
        byte[] bytes = new byte[len];
        buffer.get(bytes);
        return new String(bytes);
    }

    /**
     * Reads a single message from the socket, returning it as a sub class of Message
     * @param socket socket to read from
     * @param dataBuffer the data buffer to use
     * @return a message if it could be parsed
     * @throws IOException if the message could not be converted.
     */
    public static Message nextMessageFromSocket(SocketChannel socket, ByteBuffer dataBuffer) throws IOException {

        // read the first 4 bytes to get the message length.
        ensureBytesAvailable(socket, dataBuffer, 4);
        int length = dataBuffer.getInt();

        // read the rest of the message (as denoted by length)
        ensureBytesAvailable(socket, dataBuffer, length);

        // we now get the message type from the payload and see what type of message to create.
        // In a real world example, we may have a message factory that did this for us.
        String type = stringFromMsg(dataBuffer);
        Message msg = null;

        msg = MessageFactory.getMessage(type);

        // if we couldn't convert the message, bail out here
        if(msg == null) {
            throw new IOException("Unknown message type: " + type);
        }

        // message's fromBytes is now used to recover the rest of the fields from the payload
        msg.fromBytes(dataBuffer);
        System.out.println("Message read from socket: " + msg);
        return msg;

    }

    public static void sendMessage(SocketChannel channel, Message toSend) {
        // we need to put the message type into the buffer first.
        ByteBuffer bbMsg = ByteBuffer.allocate(26214400);
        stringToMsg(bbMsg, toSend.messageType());

        // and then any extra fields for this type of message
        toSend.toBytes(bbMsg);
        bbMsg.flip();

        // now we need to encode the length into a different buffer.
        ByteBuffer bbOverall = ByteBuffer.allocate(10);
        bbOverall.putInt(bbMsg.remaining());
        bbOverall.flip();

        // and lastly, we write the length, followed by the message.
        long written = 0;
        try {
            written = channel.write(new ByteBuffer[]{bbOverall, bbMsg});
            System.out.println("Message written to socket: " + toSend + ", length was: " + written);
        } catch (IOException e) {
            System.out.println("Cannot send message.");
        }

    }

    /**
     * When we are reading messages from the wire, we need to ensure there are
     * enough bytes in the buffer to fully decode the message. If not we keep
     * reading until we have enough.
     * @param socket the socket to read from
     * @param buffer the buffer to store the bytes
     * @param enough the amount of data required.
     * @throws IOException if the socket closes or errors out.
     */
    private static void ensureBytesAvailable(SocketChannel socket, ByteBuffer buffer, int enough) throws IOException {
        // if there's already something in the buffer, then compact it and prepare it for writing again.
        if(buffer.position() != 0) {
            buffer.compact();
        }
        // we loop until we have enough data to decode the message
        while(buffer.position() < enough) {
            // try and read, if read returns 0 or less, the socket's closed.
            int len = socket.read(buffer);
//            if(!socket.isOpen() || len <= 0) {
//                throw new IOException("Socket closed while reading");
//            }
            System.out.println("Bytes now in buffer: " + buffer.remaining() + " read from socket: " + len);
        }
        // and finally, prepare the buffer for reading.
        buffer.flip();

    }

}
