package com.example.kafka.Producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {
    final static Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {


        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        final KafkaProducer producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < 10; i++) {

            ProducerRecord<String, String> record = new ProducerRecord<>("TestTopic", "key_" + i, "value_" + i);
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        log.info("\nReceived Record Metadata::" + "\nTopic:" + recordMetadata.topic() + "\nPartation:" + recordMetadata.partition()
                                + "\nOffset:" + recordMetadata.offset() + "\n@Timestamp:" + recordMetadata.timestamp());

                    } else {
                        log.error("Error Occurred", e);
                    }
                }
            });
        }


        producer.flush();
        producer.close();


    }
}
