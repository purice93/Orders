package com.css.hib;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Room
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.css.hib.Room
 * @author MyEclipse Persistence Tools
 */

public class RoomDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RoomDAO.class);
	// property constants
	public static final String SID = "sid";
	public static final String SNAME = "sname";
	public static final String SROOM = "sroom";
	public static final String SMAJOR = "smajor";
	public static final String SCLASS = "sclass";
	public static final String SEMAIL = "semail";

	public void save(Room transientInstance) {
		log.debug("saving Room instance");
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

	public void delete(Room persistentInstance) {
		log.debug("deleting Room instance");
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

	public Room findById(java.lang.Integer id) {
		log.debug("getting Room instance with id: " + id);
		try {
			Room instance = (Room) getSession().get("com.css.hib.Room", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Room instance) {
		log.debug("finding Room instance by example");
		try {
			List results = getSession().createCriteria("com.css.hib.Room").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Room instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Room as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySid(Object sid) {
		return findByProperty(SID, sid);
	}

	public List findBySname(Object sname) {
		return findByProperty(SNAME, sname);
	}

	public List findBySroom(Object sroom) {
		return findByProperty(SROOM, sroom);
	}

	public List findBySmajor(Object smajor) {
		return findByProperty(SMAJOR, smajor);
	}

	public List findBySclass(Object sclass) {
		return findByProperty(SCLASS, sclass);
	}

	public List findBySemail(Object semail) {
		return findByProperty(SEMAIL, semail);
	}

	public List findAll() {
		log.debug("finding all Room instances");
		try {
			String queryString = "from Room";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Room merge(Room detachedInstance) {
		log.debug("merging Room instance");
		Transaction trans=getSession().beginTransaction();
		try {
			Room result = (Room) getSession().merge(detachedInstance);
			log.debug("merge successful");
			trans.commit();
			return result;
		} catch (RuntimeException re) {
			trans.rollback();
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Room instance) {
		log.debug("attaching dirty Room instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Room instance) {
		log.debug("attaching clean Room instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}