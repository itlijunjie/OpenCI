package com.itlijunjie.openci.services;

import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.Jenkins;

public interface IJenkinsService {
    /**
     * 添加用户，如果用户名已经存在抛出异常UserException
     *
     * @param jenkins 要添加的用户
     * @return 添加成功的用户
     */
    Jenkins add(Jenkins jenkins);

    /**
     * 更新用户
     *
     * @param jenkins 要更新的用户
     */
    void update(Jenkins jenkins);

    /**
     * 根据id删除用户
     *
     * @param id 要删除用户的id
     */
    void delete(int id);

    /**
     * 根据id加载用户
     *
     * @param id 要加载用户的id
     * @return 返回要加载的用户
     */
    Jenkins load(int id);

    /**
     * 列表所有的用户
     *
     * @return
     */
    PageInfo pageList(String hql, int pageNo, int pageCount);

}
