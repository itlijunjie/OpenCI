package com.itlijunjie.openci.dao;

import com.itlijunjie.openci.vo.Build;

public interface IBuildDao extends IPageBaseDAO {
    Build load(int serverId, int appId, int biuldNumber);
}
