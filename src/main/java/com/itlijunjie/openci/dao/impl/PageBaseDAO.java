package com.itlijunjie.openci.dao.impl;

import com.itlijunjie.openci.dao.IPageBaseDAO;
import com.itlijunjie.openci.util.PageInfo;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class PageBaseDAO extends HibernateDaoSupport implements IPageBaseDAO {

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

    @Override
    public int getCount(String hql) {
        try {
            System.out.println(findRec("select count(*) " + hql).get(0));
            return ((Long) findRec("select count(*) " + hql).get(0)).intValue();
        } catch (RuntimeException re) {
            throw re;
        }
    }

    public List findRec(String string) {
        try {
            return this.getHibernateTemplate().find(string);
        } catch (RuntimeException re) {
            throw re;
        }
    }

    /**
     * getPage 方法，取得第N页的信息
     */
    @Override
    public PageInfo getPage(String hql, int pageNo, int pageCount) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.pageCount = pageCount;
        pageInfo.totalCount = getCount(hql);
        // 分页信息
        if (pageInfo.totalCount % pageCount == 0) {
            pageInfo.pageTotalNum = pageInfo.totalCount / pageInfo.pageCount;
            if (pageInfo.pageTotalNum == 0)
                pageInfo.pageTotalNum = 1;
        } else
            pageInfo.pageTotalNum = pageInfo.totalCount / pageCount + 1;

        // 设置当前页
        if (pageNo < 1)
            pageInfo.curPage = 1;
        else if (pageNo > pageInfo.pageTotalNum)
            pageInfo.curPage = pageInfo.pageTotalNum;
        else
            pageInfo.curPage = pageNo;

        // 设置上页下页信息
        if (pageNo < pageInfo.pageTotalNum)
            pageInfo.haveNext = true;
        if (pageNo > 1)
            pageInfo.havePre = true;

        // 不报错的实现方法
        pageInfo.list = findRec(hql, (pageInfo.curPage - 1) * pageCount, pageCount);
        return pageInfo;

    }

    @Override
    public PageInfo getPage(String hql, int pageNo) {
        return getPage(hql, pageNo, 20);
    }

    // 按指定分页记录查询的方法
    public List findRec(final String hql, final int firstResult,
                        final int maxResults) {
        return getHibernateTemplate().executeFind(new HibernateCallback() {
            public Object doInHibernate(Session s) throws HibernateException,
                    SQLException {
                Query query = s.createQuery(hql);
                query.setFirstResult(firstResult);
                query.setMaxResults(maxResults);
                List list = query.list();
                return list;
            }
        });
    }
}