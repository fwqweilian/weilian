package com.gemepro.api.service;

import com.gemepro.api.entity.DataskyEquipmentEntity;

import java.util.List;
import java.util.Map;

public interface DataskyEquipmentService {

    DataskyEquipmentEntity queryObject(Integer id);

    List<DataskyEquipmentEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DataskyEquipmentEntity queEquipment);

    void update(DataskyEquipmentEntity queEquipment);

    void delete(List<Long> equipmentids);

    void deleteBatch(Integer[] ids);


    /**
     * 删除设备
     *
     * @param id
     * @param EquipmentIds
     */
    void deleteEquipment(Integer id, List<Integer> EquipmentIds);

    /**
     * 查询设备列表
     *
     * @param adminId
     * @param search
     * @return
     */
    List<DataskyEquipmentEntity> queryEquipmentList(Long adminId, String search);

    /**
     * 校验设备SN码
     *
     * @param mobile
     * @return
     */
    DataskyEquipmentEntity queryUserByTel(String mobile);


}