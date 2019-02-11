package com.gemepro.api.service;


import com.gemepro.api.entity.DataskyHeadEntity;
import com.gemepro.api.vo.DataSkyHeadVo;

import java.util.List;
import java.util.Map;

/***
 * 探针信息插入到数据库
 * @author fwq
 */
public interface DataSkyHeadService {

    /**
     * 插入探针返回的数据
     *
     * @param dataSkyHeadEntity
     */
    void dataSkyHeanSave(DataskyHeadEntity dataSkyHeadEntity);

    String queryObjectgetCPF(Map<String, Object> map);

    /**
     * 客户数(包括新客户和老客户)、累计驻店时长、客户/客流平均驻店时长
     *
     * @param map
     * @return
     */
    List<DataSkyHeadVo> queryObjectgetNOPF(Map<String, Object> map);

    /**
     * 累计驻店时长
     *
     * @param map
     * @return
     */
    List<DataSkyHeadVo> queryObjectgetATL(Map<String, Object> map);

    List<DataSkyHeadVo> queryObjectgetNOCIS(Map<String, Object> map);


    List<DataSkyHeadVo> queryObjectgetLDC(Map<String, Object> map);

    List<DataSkyHeadVo> queryObjectgetLCST(Map<String, Object> map);


    List<DataSkyHeadVo> queryObjectgetNONCITS(Map<String, Object> map);

}