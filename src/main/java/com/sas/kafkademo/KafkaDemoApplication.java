package com.sas.kafkademo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
        //String bootstrapServer = "[::1]:9092";
        //Properties props = new Properties();
        //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        //props.put(ProducerConfig.CLIENT_ID_CONFIG, "clientId");
        //props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
        //KafkaProducer producer = new KafkaProducer<String, String>(props);
//
        //String topic = "kafka-demo";
        //producer
        //        .send(new ProducerRecord(topic, "Hello world!"))
        //        .get()
        //            ;
        //producer.close();
    }

}
