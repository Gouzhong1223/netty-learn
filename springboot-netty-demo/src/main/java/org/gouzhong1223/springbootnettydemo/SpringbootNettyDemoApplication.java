package org.gouzhong1223.springbootnettydemo;

import io.netty.channel.ChannelFuture;
import org.gouzhong1223.springbootnettydemo.server.NettyServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootNettyDemoApplication implements CommandLineRunner {

    @Value("${netty.port}")
    private Integer port;

    @Value("${netty.url}")
    private String url;

    /**
     *注入NettyServer组件
     */
    private final NettyServer nettyServer;

    public SpringbootNettyDemoApplication(NettyServer nettyServer) {
        this.nettyServer = nettyServer;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootNettyDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = nettyServer.start(url, port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
        //服务端管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程
        future.channel().closeFuture().syncUninterruptibly();
    }
}

