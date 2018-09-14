package etl.kafkaproducer.run;

import java.io.IOException;
import java.sql.Timestamp;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import com.google.gson.Gson;
import etl.kafkaproducer.Producer;
import etl.logs.Person;

/**
 * @author atta Generate random data for demonstration and send it to
 *         kafkaserver Etl will consume and transform this data and store it in
 *         the etl db in PSQL. This just an example, for real etl import this
 *         project, generate data for your services activities
 */
public class ProducerMain {
	private static final Logger logger = Logger.getLogger(ProducerMain.class);

	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		Person person = new Person();
		Gson gson = new Gson();
		JSONObject obj;
		Producer producer = new Producer();

		for (int i = 0; i < 140; i++) {
			person.setName("Name" + i);
			person.setId(i);
			if (i < 70)
				person.setRole("admin");
			else
				person.setRole("user");
			person.setAge(50);

			obj = new JSONObject();
			if (i < 30)
				obj.put("type", "login");
			else if (i < 60)
				obj.put("type", "logout");
			else if (i < 90)
				obj.put("type", "create");
			else if (i < 120)
				obj.put("type", "delete");
			else
				obj.put("type", "update");
			obj.put("time", new Timestamp(System.currentTimeMillis()).toString());
			obj.put("Person", gson.toJson(person).toString());
			producer.send(obj.toJSONString());
		}
		producer.close();
	}
}
