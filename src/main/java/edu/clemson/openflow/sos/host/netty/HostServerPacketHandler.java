package edu.clemson.openflow.sos.host.netty;

import edu.clemson.openflow.sos.rest.RequestParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author Khayam Anjam kanjam@g.clemson.edu
 *  * TODO: Before connecting to client, compare its IP address with the one we are getting in requestParser Obj

 */
@ChannelHandler.Sharable
public class HostServerPacketHandler extends ChannelInboundHandlerAdapter {
    private static final Logger log = LoggerFactory.getLogger(HostServerPacketHandler.class);
    private RequestParser request;

    public HostServerPacketHandler() {
        this.request = request;
        log.info("Expecting Host at IP {} Port {}",
                request.getClientIP(), request.getClientPort());
    }

    public void setRequestObject(RequestParser requestObject) {
        this.request = requestObject;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Discard the received data silently.
        InetSocketAddress socketAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        log.info("Got Message from {} at Port {}",
                socketAddress.getHostName(),
                socketAddress.getPort());
        ByteBuf m = (ByteBuf) msg;
        //FullHttpRequest request = (FullHttpRequest) msg;
        log.info(m.toString());
        //((ByteBuf) msg).release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

}