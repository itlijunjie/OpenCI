package com.itlijunjie.openci.services.impl;

import com.itlijunjie.openci.dao.IAppDao;
import com.itlijunjie.openci.services.IAppService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.App;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("appService")
public class AppService implements IAppService {

    private IAppDao appDao;

    public IAppDao getAppDao() {
        return appDao;
    }

    @Resource
    public void setAppDao(IAppDao appDao) {
        this.appDao = appDao;
    }

    @Override
    public App add(App app) {
//        Jenkins u = jenkinsDao.loadByUrl(jenkins.getUrl());
//        if (u != null) throw new AppException("要添加服务器已经存在！");
        return (App) appDao.add(app);
    }

    @Override
    public void update(App app) {
        appDao.update(app);
    }

    @Override
    public void delete(int id) {
        appDao.delete(App.class, id);
    }

    @Override
    public App load(int id) {
        return (App) appDao.load(App.class, id);
    }

    @Override
    public PageInfo pageList(String hql, int pageNo, int pageCount) {
        if (hql == null) {
            hql = "from App";
        }
        if (pageCount == 0) {
            pageCount = 1;
        }
        return appDao.getPage(hql, pageNo, pageCount);
    }
}
