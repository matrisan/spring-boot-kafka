package com.github.springbootkafka.config;

import com.github.springbootkafka.pojo.KafkaDataDO;
import com.github.springbootkafka.serializer.KafkaDataDeserializer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
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
public class ConfigKafkaConsumer {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    private String batch = "2";

    private Integer consumers = 2;

    /**
     * @return 返回消费者设置
     */
    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>(5);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaDataDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "batch");
        // maximum records per poll
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, batch);
        return props;
    }

    /**
     * @return 根据消费者设置 生成消费者工厂
     */
    @Bean
    public ConsumerFactory<String, KafkaDataDO<String>> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    /**
     * @return kafka 客户端
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaDataDO<String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaDataDO<String>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(consumers);
        // enable batch listening
        factory.setBatchListener(true);
        // 增加过滤器
        factory.setRecordFilterStrategy(consumerRecord -> consumerRecord.value().getName().endsWith("1"));
        return factory;
    }

}
