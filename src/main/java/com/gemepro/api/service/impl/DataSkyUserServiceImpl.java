package com.gemepro.api.service.impl;

import com.gemepro.api.dao.DataskyUserDao;
import com.gemepro.api.entity.DataskyUserEntity;
import com.gemepro.api.service.DataskyUserService;
import com.gemepro.api.vo.DataSkyUserEquipmentVo;
import com.gemepro.common.exception.RRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dataSkyUserService")
public class DataSkyUserServiceImpl implements DataskyUserService {

    @Autowired
    private DataskyUserDao dataskyUserDao;

    @Override
    public DataskyUserEntity queryObject(String id) {
        return dataskyUserDao.queryObject(id);
    }

    @Override
    public List<DataskyUserEntity> queryList(Map<String, Object> map) {
        return null;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return 0;
    }

    @Override
    public void save(DataskyUserEntity queUser) {
        dataskyUserDao.save(queUser);
    }

    @Override
    public void update(DataskyUserEntity queUser) {
        dataskyUserDao.update(queUser);
    }

    @Override
    public void delete(List<Long> userids) {
        userids.forEach(userid -> {
            DataskyUserEntity bean = dataskyUserDao.queryObject(userid);
            if (bean == null) {
                throw new RRException("店铺不存在");
            }
            //将状态修改为停用
            dataskyUserDao.update(bean);
        });
    }


    /**
     * 管理员登陆
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public DataskyUserEntity adminLogin(String username, String password) {
        return dataskyUserDao.adminLogin(username, password);
    }

    /**
     * 业务员登陆
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public DataskyUserEntity queManLogin(String username, String password) {
        return dataskyUserDao.queManLogin(username, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return
     */
    @Override
    public DataskyUserEntity queryUserByTel(String mobile) {
        return dataskyUserDao.queryUserByTel(mobile);
    }

    /**
     * 删除用户
     *
     * @param id
     * @param userIds
     */
    @Override
    public void deleteUser(Integer id, List<Integer> userIds) {
        dataskyUserDao.delete(id);
    }

    /**
     * 查询用户列表
     *
     * @param adminId
     */
    @Override
    public List<DataSkyUserEquipmentVo> queryUserList(Long adminId, String search) {
        return dataskyUserDao.queryUserList(adminId, search);
    }

    @Override
    public List<DataSkyUserEquipmentVo> queryUserList(String adminId, String search) {
        return null;
    }

    /**
     * 修改密码
     *
     * @param userId  用户ID
     * @param oldpass 原密码
     * @param newpass 新密码
     */
    @Override
    public int updatePassword(Long userId, String oldpass, String newpass) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("password", oldpass);
        map.put("newPassword", newpass);
        return dataskyUserDao.updatePassword(map);
    }

    @Override
    public int updateContacts(Long userId, String oldcontacts, String newcontacts) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("oldcontacts", oldcontacts);
        map.put("newcontacts", newcontacts);
        return dataskyUserDao.updateContacts(map);
    }
}
