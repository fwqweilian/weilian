package com.gemepro.api.service;

import com.gemepro.api.entity.DataskyUserEntity;
import com.gemepro.api.vo.DataSkyUserEquipmentVo;

import java.util.List;
import java.util.Map;

public interface DataskyUserService {

    DataskyUserEntity queryObject(String id);

    List<DataskyUserEntity> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    void save(DataskyUserEntity queUser);

    void update(DataskyUserEntity queUser);

    void delete(List<Long> userids);


    /**
     * 修改密码
     *
     * @param userId  用户ID
     * @param oldpass 原密码
     * @param newpass 新密码
     */
    int updatePassword(Long userId, String oldpass, String newpass);

    int updateContacts(Long userId, String oldcontacts, String newcontacts);


    /**
     * 管理员登陆
     *
     * @param username
     * @param password
     * @return
     */
    DataskyUserEntity adminLogin(String username, String password);

    /**
     * 业务员登陆
     *
     * @param username
     * @param password
     * @return
     */
    DataskyUserEntity queManLogin(String username, String password);

    /**
     * 校验手机号
     *
     * @param mobile
     * @return
     */
    DataskyUserEntity queryUserByTel(String mobile);

    /**
     * 删除用户
     *
     * @param id
     * @param userIds
     */
    void deleteUser(Integer id, List<Integer> userIds);

    /**
     * 查询用户列表
     *
     * @param adminId
     * @return
     */
    List<DataSkyUserEquipmentVo> queryUserList(Long adminId, String search);
    List<DataSkyUserEquipmentVo> queryUserList(String adminId, String search);
}