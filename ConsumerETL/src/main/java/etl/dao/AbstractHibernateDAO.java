package etl.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

// TODO: Auto-generated Javadoc
/**
 * A class to provide Database operations
 *
 *
 */

public abstract class AbstractHibernateDAO<T, ID extends Serializable> extends HibernateDaoSupport
implements GenericDAO<T, ID> {

	private Class<T> clazz;

	/**
	 * Gets the clazz. s
	 */
	public Class<T> getClazz() {
		return clazz;
	}

	/**
	 * Instantiates a new abstract dao.
	 */
	@SuppressWarnings("unchecked")
	public AbstractHibernateDAO() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
		getHibernateTemplate().flush();
	}

	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);
		getHibernateTemplate().flush();
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
		getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findById(ID id) {
		T t = getHibernateTemplate().get(clazz, id);
		if (t == null) {
			throw new ObjectRetrievalFailureException(clazz, id);
		}
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		System.out.println("Inside Hibernate Find All method of Orcle");
		return getHibernateTemplate().loadAll(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeById(ID id) {
		getHibernateTemplate().delete(findById(id));
	}

	@Override
	public void remove(T t) {
		getHibernateTemplate().delete(t);
		getHibernateTemplate().flush();
	}
}
