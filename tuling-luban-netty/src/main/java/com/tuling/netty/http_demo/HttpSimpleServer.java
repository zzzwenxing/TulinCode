package com.tuling.netty.http_demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedNioFile;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.ContentHandler;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * Created by Tommy on 2018/1/23.
 */
public class HttpSimpleServer {
    //open 启动服务
    public void openServer() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.channel(NioServerSocketChannel.class);
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup work = new NioEventLoopGroup(8);
        bootstrap.childHandler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                ch.pipeline().addLast("http-server", new HttpServerHandler());

                ch.pipeline().addLast("WebSocket-protocol",
                        new WebSocketServerProtocolHandler("/ws")); // 封装了编码和解码操作
                ch.pipeline().addLast("WebSocket-handler",
                        new WebSocketServerHandler());// 处理业务

            }
        });
        bootstrap.group(boss, work);
        try {
            ChannelFuture future = bootstrap.bind(8080).sync();
            System.out.println("服务启动：8080");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    private static class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
            if ("/ws".equalsIgnoreCase(msg.uri())) {
                ctx.fireChannelRead(msg.retain());
                return;
            }
            File f=new File("E:\\git\\tuling-teach-netty\\src\\main\\resources\\HelloWord.html");
            RandomAccessFile file = new RandomAccessFile(f, "r");//4
            DefaultHttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, file.length());
//            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderNames.KEEP_ALIVE);
            ctx.write(response);
            ctx.write(new ChunkedNioFile(file.getChannel()));
            ChannelFuture future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
            future.addListener(ChannelFutureListener.CLOSE);
            file.close();
        }
    }

    private static class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
            System.out.println("接收数据:"+msg.text());
            ctx.writeAndFlush(new TextWebSocketFrame("hello word"));
        }
    }

    public static void main(String[] args) {
        HttpSimpleServer simpleServer = new HttpSimpleServer();
        simpleServer.openServer();
    }


}
