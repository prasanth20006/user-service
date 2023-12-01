package com.jpmc.webflux.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpmc.webflux.dao.UserDao;
import com.jpmc.webflux.dto.UserDocumentDto;
import com.jpmc.webflux.repository.UserDocumentRepository;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;
import reactor.kafka.sender.SenderRecord;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;


@Component
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class.getName());

    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String TOPIC = "demo-topic";

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public UserDocumentRepository userDocumentRepository;

    @Autowired
    private UserDao userDao;

    @Value("${topic.name}")
    private String orderTopic;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

   /* public KafkaProducer(String bootstrapServers) {

        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "sample-producer");
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        SenderOptions<Integer, String> senderOptions = SenderOptions.create(props);

        sender = KafkaSender.create(senderOptions);
        dateFormat = DateTimeFormatter.ofPattern("HH:mm:ss:SSS z dd MMM yyyy");
    }*/

    public Mono<UserDocumentDto> sendMessages(UserDocumentDto userDocumentDto)  {
        try {
            String userDocumentAsMessage = objectMapper.writeValueAsString(userDocumentDto);
            kafkaTemplate.send(orderTopic, userDocumentAsMessage);
            log.info("User Document Details {}", userDocumentAsMessage);

        }
        catch(JsonProcessingException je){
            je.getMessage();
        }
        return userDao.createUserDocument(userDocumentDto);
    }

}