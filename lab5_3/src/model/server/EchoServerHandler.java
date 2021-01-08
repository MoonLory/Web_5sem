package model.server;

import java.io.IOException;
import java.util.Map;

import exception.ServerException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundInvoker;
import io.netty.util.CharsetUtil;

/**
 * Handler implementation for the echo server.
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	
	Map<Channel, ServerHandler> users;
	
	class OnClose implements ChannelFutureListener {
        ServerHandler user;
        OnClose(ServerHandler user){
            this.user = user;
        }
        public void operationComplete(ChannelFuture future) throws IOException {
            users.remove(user);
            if (user.getUserName() != null)
                broadcast("[" + user.getUserName() + "]: " + " has left chat", null);
        }
    }
	
	public EchoServerHandler(Map<Channel, ServerHandler> users) {
		this.users = users;
    }
	
	@Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();
        users.put(incoming, new ServerHandler());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
    	ServerHandler user = users.get(ctx.channel());
        user.append(((ByteBuf)msg).toString(CharsetUtil.UTF_8));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws ServerException, IOException {
        Channel incoming = ctx.channel();
        ServerHandler user = users.get(incoming);
        StringBuilder msg = user.getMessage();
        if (msg.length() == 0) {
            ChannelFuture future = ctx.channel().close();
            future.addListener(new OnClose(user));
            return;
        }
        if (msg.charAt(0) == '!'){
            try {
                user.init(msg.substring(1));
            } catch (IOException e) {
                System.out.println("Initialization error");
                e.printStackTrace();
                msg.setCharAt(0 ,'0');
            }
        } else if (user.getUserName() == null)
            msg.setCharAt(0 ,'0');
        
        
        if(msg.charAt(0) == '!') {
        	broadcast("[" + user.getUserName() + "]" + " has entered chat!", user);
        }
        else {
        	/*if(msg.length() == 0) {
        		return;
        	}
        	if(msg.length() == 2) {
        		return;
        	}*/
            String serverMessage = "[" + user.getUserName() + "]: " + msg;
            broadcast(serverMessage, user);
        }
        user.clear();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws ServerException {
        ctx.close();
        throw new ServerException("exceptionCaught", cause);
    }
    
    
    /**
     * Delivers a message from one user to others (broadcasting)
     * @param message
     * @param excludeUser
     * @throws IOException 
     */
    void broadcast(String message, ServerHandler excludeUser) throws IOException {
        for (Map.Entry<Channel, ServerHandler>  aUser : users.entrySet()) {
            if (aUser.getValue() != excludeUser) {
            	System.out.println(message);
                ((ChannelOutboundInvoker)aUser.getKey()).writeAndFlush(Unpooled.copiedBuffer(message, CharsetUtil.UTF_8));
            }
        }
    }
	
}
