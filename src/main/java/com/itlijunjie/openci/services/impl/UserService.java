package com.itlijunjie.openci.services.impl;

import com.itlijunjie.openci.dao.IUserDao;
import com.itlijunjie.openci.services.IUserService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.util.StringUtil;
import com.itlijunjie.openci.vo.User;
import com.itlijunjie.openci.vo.exception.UserException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService implements IUserService {

    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User add(User user) {
        User u = userDao.loadByUsername(user.getUsername());
        if (u != null) throw new UserException("要添加的用户对象已经存在！");
        return (User) userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(User.class, id);
    }

    @Override
    public User load(int id) {
        return (User) userDao.load(User.class, id);
    }

    @Override
    public PageInfo pageList(String hql, int pageNo, int pageCount) {
        if (hql == null) {
            hql = "from User";
        }
        if (pageCount == 0) {
            pageCount = 1;
        }
        return userDao.getPage(hql, pageNo, pageCount);
    }

    @Override
    public User login(String username, String password) {
        User u = userDao.loadByUsername(username);
        if (u == null) {
            throw new UserException("登陆的用户不存在");
        }
        System.out.println(StringUtil.MD5(password));
        if (!StringUtil.MD5(password).equals(u.getPassword())) {
            throw new UserException("登陆用户的密码不正确");
        }
        return u;
    }
}
