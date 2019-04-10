package com.github.springbootkafka.serializer;

import com.alibaba.fastjson.JSON;
import com.github.springbootkafka.pojo.KafkaDataDO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * <p>
 * 创建时间为 15:10 2019-04-09
 * 项目名称 spring-boot-kafka
 * </p>
 *
 * @author shao
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j

public class KafkaDataSerializer implements Serializer<KafkaDataDO<String>> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        String propertyName = isKey ? "key.serializer.encoding" : "value.serializer.encoding";
        Object encodingValue = configs.get(propertyName);
        if (encodingValue == null) {
            encodingValue = configs.get("serializer.encoding");
        }
        if (encodingValue instanceof String) {
            encoding = (String) encodingValue;
        }
    }

    @SneakyThrows(UnsupportedEncodingException.class)
    @Override
    public byte[] serialize(String s, KafkaDataDO<String> stringKafkaDataDTO) {
        return JSON.toJSONString(stringKafkaDataDTO).getBytes(encoding);
    }

    @Override
    public void close() {

    }
}
