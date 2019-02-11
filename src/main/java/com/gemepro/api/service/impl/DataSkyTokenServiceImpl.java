package com.gemepro.api.service.impl;

import com.gemepro.api.dao.DataTokenDao;
import com.gemepro.api.entity.DataSkyTokenEntity;
import com.gemepro.api.service.DataSkyTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("dataSkyTokenService")
public class DataSkyTokenServiceImpl implements DataSkyTokenService {

    //30天后过期
    private final static long EXPIRE = 3600 * 24 * 30;

    /**
     *
     */
    @Autowired
    private DataTokenDao dataTokenDao;


    @Override
    public DataSkyTokenEntity queryObject(Integer id) {
        return dataTokenDao.queryObject(id);
    }

    @Override
    public List<DataSkyTokenEntity> queryList(Map<String, Object> map) {
        return dataTokenDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return dataTokenDao.queryTotal(map);
    }

    @Override
    public void save(DataSkyTokenEntity dataToken) {
        dataTokenDao.save(dataToken);
    }

    @Override
    public void update(DataSkyTokenEntity dataToken) {
        dataTokenDao.update(dataToken);
    }

    @Override
    public void delete(Integer id) {
        dataTokenDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        dataTokenDao.deleteBatch(ids);
    }

    @Override
    public DataSkyTokenEntity queryByUserId(Long userId, Integer tag) {
        return null;
    }

    public DataSkyTokenEntity queryByUserId(String userId, Integer tag) {
        return dataTokenDao.queryByUserId(userId, tag);
    }

    @Override
    public DataSkyTokenEntity queryByToken(String token) {
        return dataTokenDao.queryByToken(token);
    }

    /**
     * 生成token
     *
     * @param userId 用户ID
     * @param tag
     * @return 返回token相关信息
     */
    @Override
    public Map<String, Object> createToken(String userId, Integer tag) {
        //生成一个token
        String token = UUID.randomUUID().toString();
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        DataSkyTokenEntity tokenEntity = queryByUserId(userId, tag);
        if (tokenEntity == null) {
            tokenEntity = new DataSkyTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setCreateTime(now);
            tokenEntity.setExpireTime(expireTime);
            tokenEntity.setTag(tag.toString());
            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);
            //更新token
            update(tokenEntity);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);
        map.put("tag", tag);
        return map;
    }
}
