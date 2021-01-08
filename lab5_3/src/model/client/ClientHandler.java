package model.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Client Handler class
 * 
 * @author Grishkin Andrei
 * @version 1.0
 */
@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {


    private String name;
    
    /**
     * Creates a client-side handler.
     */
    ClientHandler(String name) {
        this.name = name;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("active");
        ctx.writeAndFlush(Unpooled.copiedBuffer("!"+name, CharsetUtil.UTF_8));
    }  

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable cause) {
        cause.printStackTrace();
        channelHandlerContext.close();
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object in) throws Exception {
    	System.out.println("\n" + ((ByteBuf)in).toString(CharsetUtil.UTF_8));
    	if (name != null) {
            System.out.print("[" + name + "]: ");
        }
    }
    
}