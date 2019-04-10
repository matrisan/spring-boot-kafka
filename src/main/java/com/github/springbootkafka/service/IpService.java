package com.github.springbootkafka.service;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * <p>
 * 创建时间为 16:39 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class IpService {

    @SneakyThrows(UnknownHostException.class)
    public String getIPAddress() {
        InetAddress address = InetAddress.getLocalHost();
        //返回IP地址
        return address.getHostAddress();
    }

}
