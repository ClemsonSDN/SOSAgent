package edu.clemson.openflow.sos.agent.netty;

import edu.clemson.openflow.sos.host.netty.HostServerChannelDecoder;
import edu.clemson.openflow.sos.host.netty.HostServerChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class AgentServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("bytesDecoder",
                new ByteArrayDecoder());

        socketChannel.pipeline().addLast(
                new AgentServerChannelHandler());
        socketChannel.pipeline().addLast("bytesEncoder", new ByteArrayEncoder());

    }
}
