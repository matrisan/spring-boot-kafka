package com.github.springbootkafka.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.system.SystemProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.Iterator;

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
//        for (PropertySource<?> source : ((AbstractEnvironment) environment).getPropertySources()) {
//            if (source instanceof MapPropertySource) {
//                ((MapPropertySource) source).getSource().forEach((k, v) -> {
//                    System.out.print (k + "       ");
//                    System.out.println(v);
//                    System.out.println();
//                });
//
//            }
//        }

    }
}
