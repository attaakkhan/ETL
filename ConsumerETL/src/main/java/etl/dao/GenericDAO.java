package etl.dao;

import java.io.Serializable;
import java.util.List;

/**
 * A generic class to provide Database operations
 *
 *
 */
public interface GenericDAO<T, ID extends Serializable> {

	/**
	 * Save object in database.
	 */
	public void save(T t);

	/**
	 * Update object in database.
	 * 
	 */
	public void update(T t);

	/**
	 * Save object if it is new or update if the object is already in database.
	 */
	public void saveOrUpdate(T t);

	/**
	 * Retrieve the object from database by id.
	 */
	public T findById(ID id);

	/**
	 * Retrieve all the object from database not using where clause.
	 * 
	 */
	public List<T> findAll();

	/**
	 * Delete the object from database by id.
	 */
	public void removeById(ID id);

	/**
	 * Delete the object from database
	 */
	public void remove(T t);

}
