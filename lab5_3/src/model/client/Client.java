package model.client;

import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

import exception.ClientConnectionException;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.io.*;
 
/**
 * This is the chat client program.
 *
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Client extends Thread{
	
    private String hostname;
    Socket socket;
    private int port;
    
    
    private String userName;
 
    /**
     * Constuctor of client
     * @param hostname host name
     * @param port port
     */
    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        Scanner in = new Scanner(System.in);
    	
    	System.out.println("\nEnter your name: ");
    	this.userName = in.nextLine();
    }
    
    /**
     * Connects to the server then enters the processing loop.
     */
    public void run() {
    	
    	EventLoopGroup group = new NioEventLoopGroup();
    	
    	try {
            Bootstrap clientBootstrap = new Bootstrap();
            clientBootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(hostname, port)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                        	ChannelPipeline p = ((Channel) ch).pipeline();
                            p.addLast(new ClientHandler(userName));
                        }
                    });

            ChannelFuture channelFuture = clientBootstrap.connect().sync();
            Scanner in = new Scanner(System.in);
            String text;
            
            do {
            	System.out.print("[" + userName + "]: ");
            	text = in.nextLine();
            	channelFuture.channel().writeAndFlush(Unpooled.copiedBuffer(text, CharsetUtil.UTF_8));
     
            } while (!text.equals("bye"));
            channelFuture.channel().closeFuture().sync();
        } catch (Exception  e) {
        	e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    	
    }
    
    /**
     * setter name of client
     *
     */
    void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * enter name of client
     *
     * @return client name
     */
    String getUserName() {
        return this.userName;
    }
 
    /**
     * Runs the client as an application with a closeable frame.
     * @param args args of comand line
     */
    public static void main(String[] args) {
        Client client = new Client("192.168.0.102", 8080);
        client.run();
    }
}
