package com.github.springbootkafka.consumer;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 创建时间为 16:05 2019-08-05
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
@ActiveProfiles("junit")
@RunWith(SpringRunner.class)
@EmbeddedKafka(controlledShutdown = true, topics = "foo")
public class KafkaConsumerTest {

    @Resource
    private EmbeddedKafkaRule embeddedKafka;

    @Before
    public void setUp() {
        Map<String, Object> senderProperties = KafkaTestUtils.senderProps(embeddedKafka.getBrokersAsString());
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(senderProperties);
        template = new KafkaTemplate<>(producerFactory);
    }


    @Test
    public void onMessage() {
    }
}
