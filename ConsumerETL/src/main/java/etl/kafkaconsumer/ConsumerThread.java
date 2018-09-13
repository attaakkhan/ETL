package etl.kafkaconsumer;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

/**
 * The Class ConsumerThread.
 */
public class ConsumerThread implements Runnable {
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ConsumerThread.class);
	private final KafkaConsumer<String, String> consumer;
	private final String topic;

	public ConsumerThread(String brokers, String groupId, String topic) {
		Properties props = new Properties();
		props.put("bootstrap.servers", brokers);
		props.put("group.id", groupId);
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("auto.offset.reset", "earliest");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		this.consumer = new KafkaConsumer<>(props);
		this.topic = topic;
		this.consumer.subscribe(Arrays.asList(this.topic));
	}

	@Override
	public void run() {
		logger.info("Thread created for Consumer, ThreadId: " + Thread.currentThread().getId());
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				logger.info("******Message:" + record.value() + " **** Partition: " + record.partition()
				+ " **** Offset: " + record.offset());
			}
		}
	}
}