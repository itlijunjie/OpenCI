package com.itlijunjie.openci.services;

import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.User;

public interface IUserService {
    /**
     * 添加用户，如果用户名已经存在抛出异常UserException
     *
     * @param user 要添加的用户
     * @return 添加成功的用户
     */
    User add(User user);

    /**
     * 更新用户
     *
     * @param user 要更新的用户
     */
    void update(User user);

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
    User load(int id);

    /**
     * 列表所有的用户
     *
     * @return
     */
    PageInfo pageList(String hql, int pageNo, int pageCount);

    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @param password 用户密码
     * @return 返回登陆成功的用户对象，不成功抛出UserException
     */
    User login(String username, String password);

}
