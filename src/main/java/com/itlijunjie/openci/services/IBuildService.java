package com.itlijunjie.openci.services;

import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.Build;

public interface IBuildService {
    /**
     * 添加用户，如果用户名已经存在抛出异常AppException
     *
     * @param build 要添加的用户
     * @return 添加成功的用户
     */
    Build add(Build build);

    /**
     * 更新用户
     *
     * @param build 要更新的用户
     */
    void update(Build build);

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
    Build load(int id);

    Build load(int serverId, int appId, int biuldNumber);

    /**
     * 列表所有的用户
     *
     * @return
     */
    PageInfo pageList(String hql, int pageNo, int pageCount);

}
