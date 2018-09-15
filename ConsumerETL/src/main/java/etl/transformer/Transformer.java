package etl.transformer;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import etl.context.ETLContext;
import etl.dao.psotgreImpl.ActivityDaoHibernateImpl;
import etl.dao.psotgreImpl.LoginDaoHibernateImpl;
import etl.kafkaconsumer.ConsumerThread;
import etl.pojo.Activity;
import etl.pojo.Login;

/**
 * @author atta Tranform you data here, method transform will be called whenever
 *         message is received
 *
 */
public class Transformer {
	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ConsumerThread.class);

	/** Dao object for Activity Postgre Table. */
	public static ActivityDaoHibernateImpl activityDao = ETLContext.getBean(ActivityDaoHibernateImpl.class);

	/** Dao object for login Postgre Table. */
	public static LoginDaoHibernateImpl loginDao = ETLContext.getBean(LoginDaoHibernateImpl.class);

	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

	/**
	 * Tranaform data in messeges and Load to psql
	 * 
	 * @throws ParseException
	 * @throws                java.text.ParseException
	 */
	public static void transform(String msg) throws ParseException, java.text.ParseException {
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(msg);
		JSONObject person = (JSONObject) parser.parse(json.get("Person").toString());
		Date parsedDate = dateFormat.parse(json.get("time").toString());
		java.sql.Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());

		/** Loading all activities to postgre */
		Activity activity = new Activity();
		activity.setName(person.get("name").toString());
		activity.setPersonId(Integer.parseInt(person.get("id").toString()));
		activity.setRole(person.get("role").toString());
		activity.setType(json.get("type").toString());
		activity.setTime(timestamp);

		activityDao.save(activity);

		/**
		 * if there there is a login activity load that person entry to login table in
		 * psql
		 */
		if (json.get("type").toString().compareTo("login") == 0) {

			Login login = new Login();
			login.setAge(Integer.parseInt(person.get("age").toString()));
			login.setName(person.get("name").toString());
			login.setPersonId(Integer.parseInt(person.get("id").toString()));
			login.setRole(person.get("role").toString());
			login.setTime(timestamp);

			loginDao.save(login);

		}

	}

}
