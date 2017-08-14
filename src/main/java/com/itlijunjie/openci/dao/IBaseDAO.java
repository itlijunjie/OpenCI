package com.itlijunjie.openci.dao;

@SuppressWarnings("rawtypes")
public interface IBaseDAO {
	Object add(Object object);
	void update(Object object);
	void delete(Class clazz,int id);
	Object load(Class clazz,int id);
}