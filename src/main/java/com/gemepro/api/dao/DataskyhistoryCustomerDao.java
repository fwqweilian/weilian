package com.gemepro.api.dao;

import com.gemepro.api.entity.DataskyhistoryCustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DataskyhistoryCustomerDao {


    int deleteByPrimaryKey(Integer id);

    int insert(DataskyhistoryCustomerEntity record);

    int insertSelective(DataskyhistoryCustomerEntity record);

    DataskyhistoryCustomerEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataskyhistoryCustomerEntity record);

    int updateByPrimaryKey(DataskyhistoryCustomerEntity record);
}