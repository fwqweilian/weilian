package com.gemepro.api.dao;

import com.gemepro.api.entity.DataSkyTokenEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTokenDao extends BaseDao<DataSkyTokenEntity> {

    DataSkyTokenEntity queryByUserId(@Param("userId") String userId, @Param("tag") Integer tag);

    DataSkyTokenEntity queryByToken(String token);
}