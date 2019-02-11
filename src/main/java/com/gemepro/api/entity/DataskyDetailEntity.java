package com.gemepro.api.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author
 */
public class DataskyDetailEntity implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 采集到的手机 mac 地址
     */
    private String mac;

    /**
     * 手机的信号强度，如 rssi=-75,则信号强度为-75dbm
     */
    private String rssi;

    /**
     * 探针到手机的距离
     */
    private String range;

    /**
     * 时间戳，采集到这些 mac 的时间
     */
    private Date time;


    /**
     * WIFI探针设备id
     */
    private String dataskyid;

    /**
     * 上报时间
     */
    private Date datatime;

    /**
     * 备用
     */
    private String remarks;

    /**
     * 每个Mac对应的一系列时间
     */
    private Date macTime;

    /**
     *
     */
    private String ms;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataskyDetailEntity that = (DataskyDetailEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(mac, that.mac) &&
                Objects.equals(rssi, that.rssi) &&
                Objects.equals(range, that.range) &&
                Objects.equals(time, that.time) &&
                Objects.equals(dataskyid, that.dataskyid) &&
                Objects.equals(datatime, that.datatime) &&
                Objects.equals(remarks, that.remarks) &&
                Objects.equals(macTime, that.macTime) &&
                Objects.equals(ms, that.ms);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mac, rssi, range, time, dataskyid, datatime, remarks, macTime, ms);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDataskyid() {
        return dataskyid;
    }

    public void setDataskyid(String dataskyid) {
        this.dataskyid = dataskyid;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getMacTime() {
        return macTime;
    }

    public void setMacTime(Date macTime) {
        this.macTime = macTime;
    }
}