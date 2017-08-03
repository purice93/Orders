package com.css.hib;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Visitor entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.css.hib.Visitor
 * @author MyEclipse Persistence Tools
 */

public class VisitorDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(VisitorDAO.class);
	// property constants
	public static final String VISITOR = "visitor";
	public static final String VISITORNUMBER = "visitornumber";
	public static final String VISITORNAME = "visitorname";
	public static final String VISITORCONNECTION = "visitorconnection";
	public static final String THING = "thing";
	public static final String WATCH = "watch";

	public void save(Visitor transientInstance) {
		log.debug("saving Visitor instance");
		Transaction trans=getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			trans.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			trans.rollback();
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Visitor persistentInstance) {
		log.debug("deleting Visitor instance");
		Transaction trans=getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			trans.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			trans.rollback();
			log.error("delete failed", re);
			throw re;
		}
	}

	public Visitor findById(java.lang.Integer id) {
		log.debug("getting Visitor instance with id: " + id);
		try {
			Visitor instance = (Visitor) getSession().get(
					"com.css.hib.Visitor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Visitor instance) {
		log.debug("finding Visitor instance by example");
		try {
			List results = getSession().createCriteria("com.css.hib.Visitor")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Visitor instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Visitor as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByVisitor(Object visitor) {
		return findByProperty(VISITOR, visitor);
	}

	public List findByVisitornumber(Object visitornumber) {
		return findByProperty(VISITORNUMBER, visitornumber);
	}

	public List findByVisitorname(Object visitorname) {
		return findByProperty(VISITORNAME, visitorname);
	}

	public List findByVisitorconnection(Object visitorconnection) {
		return findByProperty(VISITORCONNECTION, visitorconnection);
	}

	public List findByThing(Object thing) {
		return findByProperty(THING, thing);
	}

	public List findByWatch(Object watch) {
		return findByProperty(WATCH, watch);
	}

	public List findAll() {
		log.debug("finding all Visitor instances");
		try {
			String queryString = "from Visitor";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Visitor merge(Visitor detachedInstance) {
		log.debug("merging Visitor instance");
		try {
			Visitor result = (Visitor) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Visitor instance) {
		log.debug("attaching dirty Visitor instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Visitor instance) {
		log.debug("attaching clean Visitor instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}