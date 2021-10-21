package com.sas.kafkademo.util;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Component
public class DemoProducer {
    /*private KafkaProducer producer;

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
    }*/

    private static final Logger logger = LoggerFactory.getLogger(DemoProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.producer.topic}")
    private String topic;

    public void sendMessage(String message) {
        String key = generateKey();
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, message);
        kafkaTemplate.send(record);
    }

    @Async
    public void sendMessageCallback(String message) {
        String key = generateKey();

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                logger.error("unable to send message=" + message, ex);
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("sent message= " + message + " with offset= " + result.getRecordMetadata().offset());
            }
        });

    }

    private static String generateKey() {
        Random random = new Random();
        int min, max;
        min = 10000;
        max = 99999;
        int res = random.nextInt(max - min) + min;
        return String.format("%s%s", new SimpleDateFormat("yyyyMMddHHmmssSSS0").format(new Date()), res);
    }

}
