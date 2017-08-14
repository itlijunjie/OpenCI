package com.itlijunjie.openci.services.impl;

import com.itlijunjie.openci.dao.IJenkinsDao;
import com.itlijunjie.openci.services.IJenkinsService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.util.StringUtil;
import com.itlijunjie.openci.vo.Jenkins;
import com.itlijunjie.openci.vo.exception.JenkinsException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("jenkinsService")
public class JenkinsService implements IJenkinsService {

    private IJenkinsDao jenkinsDao;

    public IJenkinsDao getJenkinsDao() {
        return jenkinsDao;
    }

    @Resource
    public void setJenkinsDao(IJenkinsDao jenkinsDao) {
        this.jenkinsDao = jenkinsDao;
    }

    @Override
    public Jenkins add(Jenkins jenkins) {
        Jenkins u = jenkinsDao.loadByUrl(jenkins.getUrl());
        if (u != null) throw new JenkinsException("要添加服务器已经存在！");
        return (Jenkins) jenkinsDao.add(jenkins);
    }

    @Override
    public void update(Jenkins jenkins) {
        jenkinsDao.update(jenkins);
    }

    @Override
    public void delete(int id) {
        jenkinsDao.delete(Jenkins.class, id);
    }

    @Override
    public Jenkins load(int id) {
        return (Jenkins) jenkinsDao.load(Jenkins.class, id);
    }

    @Override
    public PageInfo pageList(String hql, int pageNo, int pageCount) {
        if (hql == null) {
            hql = "from Jenkins";
        }
        if (pageCount == 0) {
            pageCount = 1;
        }
        return jenkinsDao.getPage(hql, pageNo, pageCount);
    }
}
