package etl.kafkaproducer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Producer class
 */
public class Producer {
	private static final Logger logger = Logger.getLogger(Producer.class);
	org.apache.kafka.clients.producer.Producer producer = null;
	InputStream inputStream;

	/**
	 * get value properties file
	 */
	public String getPropertyFromFile(String pName) throws IOException {

		String servers = "";
		try {
			Properties prop = new Properties();
			String propFileName = "etl.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Producer FIle '" + propFileName + "Not found");
			}
			servers = prop.getProperty(pName);
			logger.info("****** Servers loaded from properties file");
		} catch (Exception e) {
			logger.info("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return servers;
	}

	Producer() throws IOException {
		Properties configProperties = new Properties();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, getPropertyFromFile("bootstrapservers"));
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.ByteArraySerializer");
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer(configProperties);
	}


	/**
	 * Send message to kafka brokers
	 */
	public void send(String message) throws IOException {
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(getPropertyFromFile("topic"),
				message);
		logger.info("Sending Message to brokers");
		producer.send(record);
	}


	/**
	 * Unsubscribe from a topic
	 */
	public void close() {
		producer.close();
	}
}
