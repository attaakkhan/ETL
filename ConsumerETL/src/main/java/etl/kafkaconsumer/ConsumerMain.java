package etl.kafkaconsumer;

import java.io.IOException;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class ConsumerMain.
 * class for activating the ETL consumer in multithreads
 */
public class ConsumerMain {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ConsumerMain.class);

	public static void main(String[] args) {
		logger.info("**************** ETL listening to Kafka Logs");

		ConfigurationProperties prop = new ConfigurationProperties();
		ConsumerGroup consumerGroup;
		try {
			consumerGroup = prop.getConsumersFromFile();
			logger.info("******************** brokers:" + consumerGroup.getBrokers());
			logger.info("******************** topic:" + consumerGroup.getTopic());
			logger.info("****************** groupId:" + consumerGroup.getGroupId());
			logger.info("**************** Consumers:" + consumerGroup.getNumberOfConsumers());
			consumerGroup.consume();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}