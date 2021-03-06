package org.gouzhong1223.springbootnettydemo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.gouzhong1223.springbootnettydemo.handler.NettyClientHandler;

import java.net.InetSocketAddress;

/**
 * @Author : Gouzhong
 * @Blog : www.gouzhong1223.com
 * @Description :
 * @Date : create by QingSong in 2020-07-09 4:25 下午
 * @Email : gouzhong1223@gmail.com
 * @Since : JDK 1.8
 * @PackageName : org.gouzhong1223.springbootnettydemo.client
 * @ProjectName : netty-learn
 * @Version : 1.0.0
 */
public class NettyClient {
    private final String host;
    private final int port;


    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    /**
     * 运行流程：
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new NettyClient("127.0.0.1", 8089).start();
    }

    private void start() throws Exception {

        /**
         * Netty用于接收客户端请求的线程池职责如下。
         * （1）接收客户端TCP连接，初始化Channel参数；
         * （2）将链路状态变更事件通知给ChannelPipeline
         */
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler());
                        }
                    });
            //绑定端口
            ChannelFuture f = b.connect().sync();

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            group.shutdownGracefully().sync();
        }
    }
}
