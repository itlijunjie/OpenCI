package com.itlijunjie.openci.dao;

import com.itlijunjie.openci.vo.User;

public interface IUserDao extends IPageBaseDAO {

    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @return 返回用户对象
     */
    User loadByUsername(String username);

}
