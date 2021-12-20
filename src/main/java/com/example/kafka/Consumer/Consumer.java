package com.example.kafka.Consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {


    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Consumer.class);

        final String bootstrapServer = "127.0.0.1:9092";
        final String consumerGroupId = "java-group-consumer";
        Properties p = new Properties();

        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


        final KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(p);

        consumer.subscribe(Arrays.asList("TestTopic"));

        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord record : consumerRecords) {

                logger.info("record key : " + record.key());
            }
        }

    }
}
