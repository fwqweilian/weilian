package com.gemepro.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class DataskyHistoryDayEntity implements Serializable {
    private Integer id;

    /**
     * 时间戳
     */
    private Date timeStamp;

    /**
     * 客流量
     */
    private Integer passengerFlow;

    /**
     * 客户数
     */
    private Integer customer;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 设备id
     */
    private String equipmentId;

    /**
     * 第一次扫描到的时间
     */
    private Date oneTime;

    /**
     * 最后一次扫描到的时间
     */
    private Date lastTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getPassengerFlow() {
        return passengerFlow;
    }

    public void setPassengerFlow(Integer passengerFlow) {
        this.passengerFlow = passengerFlow;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
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

    public Date getOneTime() {
        return oneTime;
    }

    public void setOneTime(Date oneTime) {
        this.oneTime = oneTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DataskyHistoryDayEntity other = (DataskyHistoryDayEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTimeStamp() == null ? other.getTimeStamp() == null : this.getTimeStamp().equals(other.getTimeStamp()))
            && (this.getPassengerFlow() == null ? other.getPassengerFlow() == null : this.getPassengerFlow().equals(other.getPassengerFlow()))
            && (this.getCustomer() == null ? other.getCustomer() == null : this.getCustomer().equals(other.getCustomer()))
            && (this.getEquipmentName() == null ? other.getEquipmentName() == null : this.getEquipmentName().equals(other.getEquipmentName()))
            && (this.getEquipmentId() == null ? other.getEquipmentId() == null : this.getEquipmentId().equals(other.getEquipmentId()))
            && (this.getOneTime() == null ? other.getOneTime() == null : this.getOneTime().equals(other.getOneTime()))
            && (this.getLastTime() == null ? other.getLastTime() == null : this.getLastTime().equals(other.getLastTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTimeStamp() == null) ? 0 : getTimeStamp().hashCode());
        result = prime * result + ((getPassengerFlow() == null) ? 0 : getPassengerFlow().hashCode());
        result = prime * result + ((getCustomer() == null) ? 0 : getCustomer().hashCode());
        result = prime * result + ((getEquipmentName() == null) ? 0 : getEquipmentName().hashCode());
        result = prime * result + ((getEquipmentId() == null) ? 0 : getEquipmentId().hashCode());
        result = prime * result + ((getOneTime() == null) ? 0 : getOneTime().hashCode());
        result = prime * result + ((getLastTime() == null) ? 0 : getLastTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", timeStamp=").append(timeStamp);
        sb.append(", passengerFlow=").append(passengerFlow);
        sb.append(", customer=").append(customer);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", oneTime=").append(oneTime);
        sb.append(", lastTime=").append(lastTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}