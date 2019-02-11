package com.gemepro.api.vo;

import java.util.Date;

public class DataSkyUserEquipmentVo {

    /**
     * 店铺id
     */
    private Integer id;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备id
     */
    private String equipmentId;

    /**
     * 设备SN码
     */
    private String equipmentSn;

    /**
     * 设备说明
     */
    private String equipmentExplain;
    /**
     * 设备状态（1：使用中0：停用 -1：删除）
     */
    private Integer equipmentState;

    /**
     * 用户名
     */
    private String username;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 1:管理员,2：业务员,3:普通用户
     */
    private Integer type;

    /**
     * 店铺地址
     */
    private String address;

    /**
     * 状态（1：激活 0：停用 -1：删除）
     */
    private Integer state;

    /**
     * 权限，0：普通，1：高级
     */
    private Integer jurisdiction;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private Integer createId;

    /**
     * 店铺拥有的设备总数量
     */
    private Integer sum;

    /**
     * 店铺拥有的设备在线数量
     */
    private Integer num;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getNum() {
        return num;
    }

    public String getEquipmentExplain() {
        return equipmentExplain;
    }

    public void setEquipmentExplain(String equipmentExplain) {
        this.equipmentExplain = equipmentExplain;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentSn() {
        return equipmentSn;
    }

    public void setEquipmentSn(String equipmentSn) {
        this.equipmentSn = equipmentSn;
    }

    public Integer getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(Integer equipmentState) {
        this.equipmentState = equipmentState;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Integer jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }
}
