package com.gemepro.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class DataskyToCusEntity implements Serializable {


    /**
     * 嗅探器设备 id
     */
    private String id;

    /**
     * 手机mac
     */
    private String mac;

    /**
     * 时间戳，采集到这些 mac 的时间
     */
    private Date macTime;

    /**
     * WIFI探针设备id
     */
    private String dataskyid;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Date getMacTime() {
        return macTime;
    }

    public void setMacTime(Date macTime) {
        this.macTime = macTime;
    }

    public String getDataskyid() {
        return dataskyid;
    }

    public void setDataskyid(String dataskyid) {
        this.dataskyid = dataskyid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}