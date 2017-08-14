package com.itlijunjie.openci.services.impl;

import com.itlijunjie.openci.dao.IBuildDao;
import com.itlijunjie.openci.services.IBuildService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.Build;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("buildService")
public class BuildService implements IBuildService {

    private IBuildDao buildDao;

    public IBuildDao getBuildDao() {
        return buildDao;
    }

    @Resource
    public void setBuildDao(IBuildDao buildDao) {
        this.buildDao = buildDao;
    }

    @Override
    public Build add(Build build) {
        return (Build) buildDao.add(build);
    }

    @Override
    public void update(Build build) {
        buildDao.update(build);
    }

    @Override
    public void delete(int id) {
        buildDao.delete(Build.class, id);
    }

    @Override
    public Build load(int id) {
        return (Build) buildDao.load(Build.class, id);
    }

    public Build load(int serverId, int appId, int buildNumber) {
        return buildDao.load(serverId, appId, buildNumber);
    }

    @Override
    public PageInfo pageList(String hql, int pageNo, int pageCount) {
        if (hql == null) {
            hql = "from Build";
        }
        if (pageCount == 0) {
            pageCount = 1;
        }
        return buildDao.getPage(hql, pageNo, pageCount);
    }
}
