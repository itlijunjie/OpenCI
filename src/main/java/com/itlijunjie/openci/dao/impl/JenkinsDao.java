package com.itlijunjie.openci.dao.impl;

import com.itlijunjie.openci.dao.IJenkinsDao;
import com.itlijunjie.openci.vo.Jenkins;
import org.springframework.stereotype.Repository;

@Repository("jenkinsDao")
public class JenkinsDao extends PageBaseDAO implements IJenkinsDao {
    @Override
    public Jenkins loadByUrl(String url) {
        Jenkins j = (Jenkins) this.getSession().createQuery("from Jenkins where url = ?").setParameter(0, url).uniqueResult();
        return j;
    }
}
