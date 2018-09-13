package kafka.connection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ConfigurationProperties {
	private static final Logger logger = Logger.getLogger(ConsumerMain.class);

	InputStream inputStream;
	ConsumerGroup consumerGroup = null;

	/**
	 * Create Consumer Group from from properties file
	 */
	public ConsumerGroup getConsumersFromFile() throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = "consumer.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("Consumer FIle '" + propFileName + "Not found");
			}
			consumerGroup = new ConsumerGroup(prop.getProperty("brokers"), prop.getProperty("groupId"),
					prop.getProperty("topic"), Integer.parseInt(prop.getProperty("numberOfConsumers")));
			logger.info("****** Consumer group created from properties file");
		} catch (Exception e) {
			logger.info("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return consumerGroup;
	}
}
