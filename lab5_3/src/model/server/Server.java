package model.server;

import java.io.*;
import java.util.*;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Echo server
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Server {
	
    private int port;

    FileWriter fileWriter;
    Date date;
    
    /**
     * Constructor of server
     * @param port port
     * @throws IOException exception
     */
    public Server(int port) throws IOException {
    	date = new Date();
        this.port = port;
        fileWriter = new FileWriter("notes.txt", true);
    }
 
    /**
     * Execute method
     */
    public void execute() {
        
    	//Controller.logger.info("Chat Server is listening on port " + port);
        System.out.println("Chat Server is listening on port " + port);
        
        
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        HashMap<Channel, ServerHandler> users = new HashMap<>();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ((Channel) ch).pipeline();
                            //p.addLast(new LoggingHandler(LogLevel.INFO));
                            p.addLast(new model.server.EchoServerHandler(users));
                            //p.addLast(new EchoServerHandler(users));
                        }
                    });

            ChannelFuture f;
            f = b.bind(port).sync();
			f.channel().closeFuture().sync();
        }catch(InterruptedException e) {
        	e.printStackTrace();
        }
        finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    
    /*
     * Main method
     */
    public static void main(String[] args) {
        Server server = null;
		try {
			server = new Server(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
        server.execute();
    }
    
}
