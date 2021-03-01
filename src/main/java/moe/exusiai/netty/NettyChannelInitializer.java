package moe.exusiai.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;


public class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();


        SSLContext sslContext = SSLContextProvider.get();
        SSLEngine sslEngine = sslContext.createSSLEngine();
        sslEngine.setUseClientMode(false);

        SslHandler sslHandler = new SslHandler(sslEngine) {
            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//                ctx.pipeline().remove("ssl");
//                ctx.pipeline().remove("proxy");
//                ctx.pipeline().addLast("encoder", new HttpResponseEncoder());
//                ctx.pipeline().addLast("decoder", new HttpRequestDecoder());
//                if (cause.getCause() instanceof NotSslRecordException) {
//                    FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
//                            HttpResponseStatus.MOVED_PERMANENTLY, Unpooled.EMPTY_BUFFER);
//                    response.headers().set(HttpHeaders.Names.LOCATION, "https://map.arkpixel.tech:8124");
//                    ctx.writeAndFlush(response);
//                }
            }

        };


        pipeline.addLast("ssl", sslHandler);
        pipeline.addLast("proxy", new NettyFrontendHandler());


    }
}
