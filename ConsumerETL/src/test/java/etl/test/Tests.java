/**
 * 
 */
package etl.test;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * @author Atta add your test cases here according to requirements
 */
public class Tests {

	private static Logger logger = Logger.getLogger(Tests.class);

	/**
	 * add your test data here
	 */
	@BeforeClass
	public void init() {
		logger.info("Test:Executing before class");
	}

	/**
	 * add your test cases
	 */
	@Test
	public void test() {
		logger.info("Test:Executing Test");
	}

	/**
	 * remove test data
	 */
	@AfterClass
	public void after() {
		logger.info("Test:Executing after class");
	}
}
