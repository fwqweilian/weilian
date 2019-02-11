package com.gemepro.api.dao;

import com.gemepro.api.entity.DataskyEquipmentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataskyEquipmentDao extends BaseDao<DataskyEquipmentEntity> {
    int deleteByPrimaryKey(Integer id);

    int insert(DataskyEquipmentEntity record);

    int insertSelective(DataskyEquipmentEntity record);

    DataskyEquipmentEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataskyEquipmentEntity record);

    int updateByPrimaryKey(DataskyEquipmentEntity record);

    /**
     * 查询设备列表
     *
     * @param adminId
     * @return
     */
    List<DataskyEquipmentEntity> queryEquipmentList(@Param("adminId") Long adminId, @Param("search") String search);

    DataskyEquipmentEntity queryUserByTel(@Param("mobile") String mobile);


}