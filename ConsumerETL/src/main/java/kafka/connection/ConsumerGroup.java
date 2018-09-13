package kafka.connection;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * The Class ConsumerGroup.
 */
public class ConsumerGroup {
	private static final Logger logger = Logger.getLogger(ConsumerGroup.class);
	private final int numberOfConsumers;
	private final String groupId;
	private final String topic;
	private final String brokers;
	private List<ConsumerThread> consumerlist;

	public ConsumerGroup(String brokers, String groupId, String topic, int numberOfConsumers) {
		this.brokers = brokers;
		this.topic = topic;
		this.groupId = groupId;
		this.numberOfConsumers = numberOfConsumers;
		consumerlist = new ArrayList<>();
		for (int i = 0; i < this.numberOfConsumers; i++) {
			ConsumerThread consumer = new ConsumerThread(this.brokers, this.groupId, this.topic);
			consumerlist.add(consumer);
		}
	}

	/**
	 * create thread for each consumer
	 */
	public void consume() {
		logger.info("****** No of threads to create: " + consumerlist.size());
		for (ConsumerThread thread : consumerlist) {
			Thread t = new Thread(thread);
			t.start();
		}
	}

	/**
	 * @return the numberOfConsumers
	 */
	public int getNumberOfConsumers() {
		return numberOfConsumers;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @return the brokers
	 */
	public String getBrokers() {
		return brokers;
	}

}