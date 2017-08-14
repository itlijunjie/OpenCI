package com.itlijunjie.openci.dao;

import com.itlijunjie.openci.util.PageInfo;

@SuppressWarnings("rawtypes")
public interface IPageBaseDAO {
    Object add(Object object);

    void update(Object object);

    void delete(Class clazz, int id);

    Object load(Class clazz, int id);

    int getCount(String hql);

    PageInfo getPage(String hql, int pageNo, int pageCount);

    PageInfo getPage(String hql, int pageNo);
}