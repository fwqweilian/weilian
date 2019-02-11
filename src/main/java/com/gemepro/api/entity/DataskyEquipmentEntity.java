package com.gemepro.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class DataskyEquipmentEntity implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 设备所属商家
     */
    private Long userId;

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
     * 设备状态（1：使用中0：停用 -1：删除）
     */
    private Integer equipmentState;

    /**
     * 设备说明
     */
    private String equipmentExplain;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者id
     */
    private Long createId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEquipmentExplain() {
        return equipmentExplain;
    }

    public void setEquipmentExplain(String equipmentExplain) {
        this.equipmentExplain = equipmentExplain;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }
}