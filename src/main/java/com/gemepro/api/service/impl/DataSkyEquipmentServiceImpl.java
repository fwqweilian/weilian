package com.gemepro.api.service.impl;

import com.gemepro.api.dao.DataskyEquipmentDao;
import com.gemepro.api.entity.DataskyEquipmentEntity;
import com.gemepro.api.service.DataskyEquipmentService;
import com.gemepro.common.exception.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("dataSkyEquipmentService")
public class DataSkyEquipmentServiceImpl implements DataskyEquipmentService {

    @Autowired
    private DataskyEquipmentDao dataskyEquipmentDao;

    @Override
    public DataskyEquipmentEntity queryObject(Integer id) {
        return null;
    }

    @Override
    public List<DataskyEquipmentEntity> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void save(DataskyEquipmentEntity queEquipment) {
        dataskyEquipmentDao.save(queEquipment);
    }

    @Override
    public void update(DataskyEquipmentEntity queEquipment) {
        dataskyEquipmentDao.update(queEquipment);
    }

    @Override
    public void delete(List<Long> equipmentids) {
        equipmentids.forEach(equipmentid -> {
            DataskyEquipmentEntity bean = dataskyEquipmentDao.queryObject(equipmentid);
            if (bean == null) {
                throw new RRException("设备不存在");
            }
            //将状态修改为停用
            bean.setEquipmentState(-1);
            dataskyEquipmentDao.update(bean);
        });
    }

    @Override
    public void deleteBatch(Integer[] ids) {

    }

    /**
     * 删除设备
     *
     * @param id
     * @param EquipmentIds
     */
    @Override
    public void deleteEquipment(Integer id, List<Integer> EquipmentIds) {

    }

    /**
     * 查询设备列表
     *
     * @param adminId
     */
    @Override
    public List<DataskyEquipmentEntity> queryEquipmentList(Long adminId, String search) {

        return dataskyEquipmentDao.queryEquipmentList(adminId, search);
    }

    /**
     * 校验设备SN码
     *
     * @param mobile
     * @return
     */
    @Override
    public DataskyEquipmentEntity queryUserByTel(String mobile) {
        return dataskyEquipmentDao.queryUserByTel(mobile);
    }

}
