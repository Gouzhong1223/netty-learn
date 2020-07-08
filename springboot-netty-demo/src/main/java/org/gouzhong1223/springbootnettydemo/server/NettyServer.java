package org.gouzhong1223.springbootnettydemo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.gouzhong1223.springbootnettydemo.initializer.NettyServerHandlerInitializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @author : Gouzhong
 * @company : www.gouzhong1223.com
 * @Description :
 * @date : create by QingSong in 2020-07-08 23:30
 * @email : 1162864960@qq.com
 */
@Slf4j
@Component
public class NettyServer {

    private EventLoopGroup bossGroup = new NioEventLoopGroup();

    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    private Integer portNumber;

    @PostConstruct
    public void start() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                // 指定Channel
                .channel(NioServerSocketChannel.class)
                //使用指定的端口设置套接字地址
                .localAddress(new InetSocketAddress(portNumber))

                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)

                //设置TCP长连接,一般如果两个小时内没有数据的通信时,TCP会自动发送一个活动探测数据报文
                .childOption(ChannelOption.SO_KEEPALIVE, true)

                //将小的数据包包装成更大的帧进行传送，提高网络的负载,即TCP延迟传输
                .childOption(ChannelOption.TCP_NODELAY, true)

                .childHandler(new NettyServerHandlerInitializer());
        ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()) {
            log.info("启动 Netty Server");
        }
    }

    @PreDestroy
    public void destory() throws InterruptedException {
        bossGroup.shutdownGracefully().sync();
        workerGroup.shutdownGracefully().sync();
        log.info("关闭Netty");
    }


}
