package com.gemepro.api.service;


import com.gemepro.api.entity.DataskyToCusEntity;

/***
 * 探针信息插入到数据库
 * @author fwq
 */
public interface DataSkyToCusService {

    /**
     * 插入探针返回的数据
     *
     * @param dataskyToCusEntity
     */
    void save(DataskyToCusEntity dataskyToCusEntity);

}