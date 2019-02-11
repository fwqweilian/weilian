package com.gemepro.api.dao;

import com.gemepro.api.entity.DataskyHistoryHourEntity;

public interface DataskyHistoryHourDao {
    int deleteByPrimaryKey(Integer id);

    int insert(DataskyHistoryHourEntity record);

    int insertSelective(DataskyHistoryHourEntity record);

    DataskyHistoryHourEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataskyHistoryHourEntity record);

    int updateByPrimaryKey(DataskyHistoryHourEntity record);
}