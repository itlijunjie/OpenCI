package com.itlijunjie.openci.services.impl;

import com.itlijunjie.openci.dao.IReourceDao;
import com.itlijunjie.openci.services.IReourceService;
import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.Reource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ljj on 13/12/2016.
 * ReourceService实现
 */
@Service("reourceService")
public class ReourceService implements IReourceService {

    @Autowired
    IReourceDao reourceDao;

    @Override
    @Transactional
    public Reource add(Reource reource) {
        return (Reource)reourceDao.add(reource);
    }

    @Override
    public PageInfo pageList(String hql, int pageNo, int pageCount) {
        if (hql == null) {
            hql = "from Reource";
        }
        if (pageCount == 0) {
            pageCount = 1;
        }
        return reourceDao.getPage(hql,pageNo,pageCount);
    }

    @Override
    public void delete(int id) {
        reourceDao.delete(Reource.class, id);
    }
}
