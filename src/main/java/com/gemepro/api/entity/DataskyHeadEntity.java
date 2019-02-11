package com.gemepro.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class DataskyHeadEntity implements Serializable {


    /**
     * 嗅探器设备 id
     */
    private String id;

    /**
     * 嗅探器设备自身 WiFi mac
     */
    private String mmac;

    /**
     * 发送频率
     */
    private String rate;

    /**
     * 时间戳，采集到这些 mac 的时间
     */
    private String time;

    /**
     * 北半球，纬度
     */
    private String lat;

    /**
     * 经度
     */
    private String lon;
    /**
     * 上报的时间
     */
    private Date datatime;

    /**
     * 每次查询出来的数据
     */
    private DataskyDetailEntity[] data;

    private static final long serialVersionUID = 1L;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMmac() {
        return mmac;
    }

    public void setMmac(String mmac) {
        this.mmac = mmac;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Date getDatatime() {
        return datatime;
    }

    public void setDatatime(Date datatime) {
        this.datatime = datatime;
    }

    public DataskyDetailEntity[] getData() {
        return data;
    }

    public void setData(DataskyDetailEntity[] data) {
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}