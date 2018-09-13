package etl.dao.psotgreImpl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import etl.pojo.Activity;

/**
 * The Class .
 */
@Component
public class ActivityDaoHibernateImpl extends etl.dao.AbstractHibernateDAO<Activity, Integer> {

	/** The Constant logger. */
	private static final Logger logger = Logger.getLogger(ActivityDaoHibernateImpl.class);

	/**
	 * Inits the.
	 *
	 * @param sessionFactory the session factory
	 */
	@Autowired
	@Qualifier(value = "sessionFactoryPSql")
	public void init(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
}
