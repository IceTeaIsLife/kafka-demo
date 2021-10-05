package com.sas.kafkademo.producers;

import com.sas.kafkademo.config.DemoConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class DemoProducer {
    private KafkaProducer producer;

    public DemoProducer(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, DemoConfig.BOOTSTRAP_SERVER);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, DemoConfig.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        this.producer = new KafkaProducer<String, String>(props);
    }

    public void sendMessage(String key, String value) throws ExecutionException, InterruptedException {
        producer
                .send(new ProducerRecord(key, value))
                .get()
        ;
    }

    public void closeProducer(){
        producer.close();
    }
}
