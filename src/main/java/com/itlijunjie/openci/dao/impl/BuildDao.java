package com.itlijunjie.openci.dao.impl;

import com.itlijunjie.openci.dao.IBuildDao;
import com.itlijunjie.openci.vo.Build;
import com.itlijunjie.openci.vo.User;
import org.springframework.stereotype.Repository;

@Repository("buildDao")
public class BuildDao extends PageBaseDAO implements IBuildDao {
    @Override
    public Build load(int serverId, int appId, int buildNumber) {
        return (Build) this.getSession()
                .createQuery("from Build where serverId = ? and appId = ? and buildNumber = ?")
                .setParameter(0, serverId)
                .setParameter(1, appId)
                .setParameter(2, buildNumber)
                .uniqueResult();
    }

}
