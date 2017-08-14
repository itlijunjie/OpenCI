package com.itlijunjie.openci.dao;

import com.itlijunjie.openci.vo.Jenkins;

public interface IJenkinsDao extends IPageBaseDAO {
    /**
     * 根据服务器URL获取服务器相信信息
     *
     * @param url 服务器地址
     * @return 返回用户对象
     */
    Jenkins loadByUrl(String url);
}
