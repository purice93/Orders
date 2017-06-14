package com.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.model.TSushe;

/**
 * Data access object (DAO) for domain model class TSushe.
 * 
 * @see com.model.TSushe
 * @author MyEclipse Persistence Tools
 */

public class TSusheDAO extends HibernateDaoSupport
{
	private static final Log log = LogFactory.getLog(TSusheDAO.class);

	// property constants
	public static final String FANGJIANHAO = "fangjianhao";

	public static final String XUESHENGRENSHU = "xueshengrenshu";

	public static final String XUESHENGBANJI = "xueshengbanji";

	public static final String LOUHAO = "louhao";

	public static final String DEL = "del";

	protected void initDao()
	{
		// do nothing
	}

	public void save(TSushe transientInstance)
	{
		log.debug("saving TSushe instance");
		try
		{
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re)
		{
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TSushe persistentInstance)
	{
		log.debug("deleting TSushe instance");
		try
		{
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re)
		{
			log.error("delete failed", re);
			throw re;
		}
	}

	public TSushe findById(java.lang.Integer id)
	{
		log.debug("getting TSushe instance with id: " + id);
		try
		{
			TSushe instance = (TSushe) getHibernateTemplate().get(
					"com.model.TSushe", id);
			return instance;
		} catch (RuntimeException re)
		{
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TSushe instance)
	{
		log.debug("finding TSushe instance by example");
		try
		{
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re)
		{
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value)
	{
		log.debug("finding TSushe instance with property: " + propertyName
				+ ", value: " + value);
		try
		{
			String queryString = "from TSushe as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re)
		{
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFangjianhao(Object fangjianhao)
	{
		return findByProperty(FANGJIANHAO, fangjianhao);
	}

	public List findByXueshengrenshu(Object xueshengrenshu)
	{
		return findByProperty(XUESHENGRENSHU, xueshengrenshu);
	}

	public List findByXueshengbanji(Object xueshengbanji)
	{
		return findByProperty(XUESHENGBANJI, xueshengbanji);
	}

	public List findByLouhao(Object louhao)
	{
		return findByProperty(LOUHAO, louhao);
	}

	public List findByDel(Object del)
	{
		return findByProperty(DEL, del);
	}

	public List findAll()
	{
		log.debug("finding all TSushe instances");
		try
		{
			String queryString = "from TSushe";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re)
		{
			log.error("find all failed", re);
			throw re;
		}
	}

	public TSushe merge(TSushe detachedInstance)
	{
		log.debug("merging TSushe instance");
		try
		{
			TSushe result = (TSushe) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re)
		{
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TSushe instance)
	{
		log.debug("attaching dirty TSushe instance");
		try
		{
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TSushe instance)
	{
		log.debug("attaching clean TSushe instance");
		try
		{
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re)
		{
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TSusheDAO getFromApplicationContext(ApplicationContext ctx)
	{
		return (TSusheDAO) ctx.getBean("TSusheDAO");
	}
}