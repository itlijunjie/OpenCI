package com.itlijunjie.openci.dao.impl;

import com.itlijunjie.openci.dao.IBaseDAO;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;

@SuppressWarnings({"rawtypes", "unchecked"})
public class BaseDAO extends HibernateDaoSupport implements IBaseDAO {
    @Resource
    public void setSuperSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public Object add(Object object) {
        this.getHibernateTemplate().save(object);
        return object;
    }

    @Override
    public void update(Object object) {
        this.getHibernateTemplate().update(object);
    }

    @Override
    public void delete(Class clazz, int id) {
        Object o = this.getHibernateTemplate().load(clazz, id);
        this.getHibernateTemplate().delete(o);
    }

    @Override
    public Object load(Class clazz, int id) {
        Object o = this.getHibernateTemplate().get(clazz, id);
        return o;
    }
}