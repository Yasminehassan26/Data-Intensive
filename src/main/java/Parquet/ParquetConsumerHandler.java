package Parquet;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ParquetConsumerHandler {
    private Properties getKafkaProps() {
        // Set up Kafka consumer configuration
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "BOOTSTRAP_SERVERS");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "GROUP_ID");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return props;
    }


    public void kafkaConsumer() {
        Properties props = getKafkaProps();
        // Create Kafka consumer
        ObjectMapper mapper = new ObjectMapper();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        final String TOPIC = "weather-station";

        consumer.subscribe(Collections.singletonList(TOPIC));
        ParquetInputHandler parquetInputHandler = new ParquetInputHandler(10000);

        // Consume messages and write to Parquet files in batches
        while (true) {
            try {
                ConsumerRecords<String, String> recordsBatch = consumer.poll(Duration.ofSeconds(5));
                for (ConsumerRecord<String, String> record : recordsBatch) {
                    // Parse the message into a GenericRecord
                    WeatherStationMessage message = mapper.readValue(record.value(), WeatherStationMessage.class);
                    parquetInputHandler.messagesConsumer(message);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
