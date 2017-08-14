package com.itlijunjie.openci.services;

import com.itlijunjie.openci.util.PageInfo;
import com.itlijunjie.openci.vo.Reource;

/**
 * Created by ljj on 13/12/2016.
 * ReourceService接口
 */
public interface IReourceService {

    Reource add(Reource reource);

    PageInfo pageList(String hql, int pageNo, int pageCount);

    void delete(int id);

}
