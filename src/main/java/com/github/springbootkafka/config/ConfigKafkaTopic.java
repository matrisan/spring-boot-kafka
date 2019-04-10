package com.github.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 创建时间为 15:13 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
public class ConfigKafkaTopic {

    /**
     * @return 创建 Topic
     */
    @Bean
    public NewTopic topic1() {
        return new NewTopic("TestTopic", 32, (short) 2);
    }

}
