package com.gemepro.api.dao;

import com.gemepro.api.entity.DataskyUserEntity;
import com.gemepro.api.vo.DataSkyUserEquipmentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DataskyUserDao extends BaseDao<DataskyUserEntity> {

    /**
     * 管理员登陆
     *
     * @param username
     * @param password
     * @return
     */
    DataskyUserEntity adminLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 业务员登陆
     *
     * @param username
     * @param password
     * @return
     */
    DataskyUserEntity queManLogin(@Param("username") String username, @Param("password") String password);

    /**
     * 校验手机号
     *
     * @param mobile
     * @return
     */
    DataskyUserEntity queryUserByTel(@Param("mobile") String mobile);

    /**
     * 查询用户列表
     *
     * @param adminId
     * @return
     */
    List<DataSkyUserEquipmentVo> queryUserList(@Param("adminId") Long adminId, @Param("search") String search);

    /**
     * 修改密码
     */
    int updatePassword(Map<String, Object> map);

    int updateContacts(Map<String, Object> map);
}
