package com.itlijunjie.openci.services;

import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.App;

public interface IAppService {
    /**
     * 添加用户，如果用户名已经存在抛出异常AppException
     *
     * @param app 要添加的用户
     * @return 添加成功的用户
     */
    App add(App app);

    /**
     * 更新用户
     *
     * @param app 要更新的用户
     */
    void update(App app);

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
    App load(int id);

    /**
     * 列表所有的用户
     *
     * @return
     */
    PageInfo pageList(String hql, int pageNo, int pageCount);

}
