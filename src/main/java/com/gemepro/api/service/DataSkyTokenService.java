package com.gemepro.api.service;

import com.gemepro.api.entity.DataSkyTokenEntity;

import java.util.List;
import java.util.Map;

public interface DataSkyTokenService {
    DataSkyTokenEntity queryObject(Integer id);

    List<DataSkyTokenEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DataSkyTokenEntity queToken);

    void update(DataSkyTokenEntity queToken);

    void delete(Integer id);

    void deleteBatch(Integer[] ids);

    DataSkyTokenEntity queryByUserId(Long userId, Integer tag);

    DataSkyTokenEntity queryByToken(String token);

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @return 返回token相关信息
     */
    Map<String, Object> createToken(String userId, Integer tag);
}
