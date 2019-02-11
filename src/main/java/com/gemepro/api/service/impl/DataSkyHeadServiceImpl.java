package com.gemepro.api.service.impl;

import com.gemepro.api.dao.DataskyHeadDao;
import com.gemepro.api.entity.DataskyHeadEntity;
import com.gemepro.api.service.DataSkyHeadService;
import com.gemepro.api.vo.DataSkyHeadVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author fwq
 */
@Service("dataSkyHeadService")
public class DataSkyHeadServiceImpl implements DataSkyHeadService {

    @Autowired
    private DataskyHeadDao dataSkyHeadDao;


    /**
     * 插入探针返回的数据
     *
     * @param dataSkyHeadEntity
     */
    @Override
    public void dataSkyHeanSave(DataskyHeadEntity dataSkyHeadEntity) {
        dataSkyHeadDao.save(dataSkyHeadEntity);
    }


    @Override
    public String queryObjectgetCPF(Map<String, Object> map) {
        return null;
    }

    /**
     * 客户数(包括新客户和老客户)、累计驻店时长、客户/客流平均驻店时长
     *
     * @param map
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryObjectgetNOPF(Map<String, Object> map) {
        return null;
    }

    /**
     * 累计驻店时长
     *
     * @param map
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryObjectgetATL(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<DataSkyHeadVo> queryObjectgetNOCIS(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<DataSkyHeadVo> queryObjectgetLDC(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<DataSkyHeadVo> queryObjectgetLCST(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<DataSkyHeadVo> queryObjectgetNONCITS(Map<String, Object> map) {
        return null;
    }
}
