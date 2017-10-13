package com.generic.hibdao.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractGenericDao<E> implements GenericDao<E> {
	
	
	private final Class<E> entityClass;

	@SuppressWarnings("unchecked")
	public AbstractGenericDao() {
		this.entityClass = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}
	
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public Serializable save(E entity) {
		return getSession().save(entity);
	}

	public void saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);
		
	}

	public void delete(E entity) {
		getSession().delete(entity);
	}

	public void deleteAll() {
		List<E> entities = findAll();
		for (E entity : entities) {
			getSession().delete(entity);
		}
	}

	public List<E> findAll() {
		return getSession().createCriteria(this.entityClass).list();
	}

	public List<E> findAllByExample(E entity) {
		Example example = Example.create(entity).ignoreCase().enableLike().excludeZeroes();
		return getSession().createCriteria(this.entityClass).add(example).list();
	}

	@SuppressWarnings("unchecked")
	public E findById(Serializable id) {
		return (E) getSession().get(this.entityClass, id);
	}

	public void clear() {
		getSession().clear();
		
	}

	public void flush() {
		getSession().flush();

		
	}

}
