package model.server;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

import exception.ServerException;

/**
 * This is the chat server program.
 * 
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Server {
	
    private int port;
     
    private Map<SocketChannel, ServerHandler> users = new HashMap<SocketChannel, ServerHandler>();
    
    private ServerSocketChannel ssc;
    
    private Selector selector;
    
    private ByteBuffer buf = ByteBuffer.allocate(1024);
    
    FileWriter fileWriter;
    Date date;
    
    /**
     * Constructor of server 
     * @param port port
     * @throws IOException return exception 
     */
    public Server(int port) throws IOException {
    	date = new Date();
        this.port = port;
        this.ssc = ServerSocketChannel.open();
        this.ssc.socket().bind(new InetSocketAddress(port));
        this.ssc.configureBlocking(false);
        this.selector = Selector.open();
        this.ssc.register(this.selector, 16);
        fileWriter = new FileWriter("notes.txt", true);
    }
    
    /**
     * Execute method that run server
     */
    public void execute() {
        
    	System.out.println("Chat Server is listening on port " + port);
        
        while (this.ssc.isOpen()) {
        	try {
	        	this.selector.select();
	            Iterator iter = this.selector.selectedKeys().iterator();
	            while(iter.hasNext()) {
	            	SelectionKey key = (SelectionKey)iter.next();
	                iter.remove();
	                if (key.isAcceptable()) {
	                    this.handleAccept(key);
	                }
	
	                try {
	                    if (key.isReadable()) {
	                        this.handleRead(key);
	                    }
	                } catch (IOException var4) {
	                    key.cancel();
	                    key.channel().close();
	                }
	            }
        	} catch (IOException | ServerException ex) {
            	 System.out.println("Error in the server: " + ex.getMessage());
                    ex.printStackTrace();
                }
 
            }
    }
    
    /**
     * Add method
     * @param key key of SocketChanel
     * @throws IOException return exception
     * @throws ServerException return exception
     */
    private void handleAccept(SelectionKey key) throws IOException, ServerException {
        SocketChannel sc = ((ServerSocketChannel)key.channel()).accept();
        String address = sc.socket().getInetAddress() + ":" + sc.socket().getPort();
        sc.configureBlocking(false);
        sc.register(this.selector, 1, address);
        this.users.put(sc, new ServerHandler(this));
    }
    
    /**
     * Read method
     * @param key key of SocketChanel
     * @throws IOException return exception
     */
    private void handleRead(SelectionKey key) throws IOException {
        SocketChannel ch = (SocketChannel)key.channel();
        StringBuilder sb = new StringBuilder();
        ServerHandler user = (ServerHandler)this.users.get(ch);
        this.buf.clear();
        boolean var5 = false;
        int read;
        while((read = ch.read(this.buf)) > 0) {
            this.buf.flip();
            byte[] bytes = new byte[this.buf.limit()];
            this.buf.get(bytes);
            sb.append(new String(bytes));
            this.buf.clear();
        }

        if (read < 0) {
            this.broadcast(user.getUserName() + " has left the chat!", (ServerHandler)null);
            this.users.remove(ch);
            ch.close();
        } else {
        	if (sb.charAt(0) == '!') {
        		System.out.println(sb.substring(1,sb.length()-2));
        		user.setUserName(sb.substring(1,sb.length()-2));
        		fileWriter.write(date.toString()+" "+sb.substring(1,sb.length()-2)+"\n");
                fileWriter.flush();
                return;
        	}
        	if(sb.length() == 0) {
        		return;
        	}
        	if(sb.length() == 2) {
        		return;
        	}
        	System.out.println(sb);
        	System.out.println(sb.length());
        	System.out.println(user.getUserName());
            String serverMessage = "[" + user.getUserName() + "]: " + sb;
            broadcast(serverMessage, user);
        }
    }
    
    /**
     * Runs the server as an application with a closeable frame.
     * @param args args of command line
     */
    public static void main(String[] args) {
        Server server = null;
		try {
			server = new Server(8080);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
        server.execute();
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     * @param message message
     * @param excludeUser map of users
     * @throws IOException return exception
     */
    void broadcast(String message, ServerHandler excludeUser) throws IOException {
        for (Map.Entry<SocketChannel, ServerHandler>  aUser : users.entrySet()) {
            if (aUser.getValue() != excludeUser) {
            	System.out.println(message);
                aUser.getKey().write(ByteBuffer.wrap((message).getBytes()));
            }
        }
    }
 
    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    boolean hasUsers() {
        return !this.users.isEmpty();
    }
}
