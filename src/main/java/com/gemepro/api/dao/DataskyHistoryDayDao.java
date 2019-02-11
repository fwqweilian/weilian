package com.gemepro.api.dao;

import com.gemepro.api.entity.DataskyHistoryDayEntity;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface DataskyHistoryDayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DataskyHistoryDayEntity record);

    int insertSelective(DataskyHistoryDayEntity record);

    DataskyHistoryDayEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataskyHistoryDayEntity record);

    int updateByPrimaryKey(DataskyHistoryDayEntity record);
}