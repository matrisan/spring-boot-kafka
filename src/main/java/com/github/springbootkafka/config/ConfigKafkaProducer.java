package com.github.springbootkafka.config;

import com.github.springbootkafka.pojo.KafkaDataDO;
import com.github.springbootkafka.serializer.KafkaDataDeserializer;
import com.github.springbootkafka.serializer.KafkaDataSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

@Slf4j
@Configuration
public class ConfigKafkaProducer {

    @Resource
    private KafkaProperties kafkaProperties;

    /**
     * @return ProducerFactory
     */
    @Bean
    public ProducerFactory<String, KafkaDataDO<String>> producerFactory() {
        Map<String, Object> props = kafkaProperties.buildProducerProperties();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaDataSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    /**
     * @return KafkaTemplate
     */
    @Bean
    public KafkaTemplate<String, KafkaDataDO<String>> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
