package com.github.springbootkafka.config;

import com.github.springbootkafka.pojo.KafkaDataDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 创建时间为 15:14 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class ConfigKafkaDataConsumer {

    @Resource
    private KafkaProperties kafkaProperties;

    /**
     * @return 根据消费者设置 生成消费者工厂
     */
    @Bean
    public ConsumerFactory<String, KafkaDataDO<String>> consumerFactory() {
        Map<String, Object> props = kafkaProperties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /**
     * @return kafka 客户端
     */
    @Primary
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaDataDO<String>> kafkaListenerContainerFactory(ConsumerFactory<String, KafkaDataDO<String>> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, KafkaDataDO<String>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(2);
        // enable batch listening
        factory.setBatchListener(true);
        // 增加过滤器
//        factory.setRecordFilterStrategy(consumerRecord -> consumerRecord.value().getName().endsWith("1"));
        return factory;
    }

}
