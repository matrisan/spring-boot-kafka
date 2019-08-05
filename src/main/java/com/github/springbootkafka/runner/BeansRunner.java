package com.github.springbootkafka.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;

/**
 * <p>
 * 创建时间为 16:06 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class BeansRunner implements CommandLineRunner {

    @Resource
    private ApplicationContext context;

    @Resource
    private Environment environment;

    @Override
    public void run(String... args) throws Exception {
        InetAddress address = InetAddress.getLocalHost();
        System.out.println(address.getHostAddress());
    }
}
