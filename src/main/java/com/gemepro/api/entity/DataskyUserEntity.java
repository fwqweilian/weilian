package com.gemepro.api.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class DataskyUserEntity implements Serializable {
    private String USERID;        //用户id
    private String USERNAME;    //用户名
    private String PASSWORD;    //密码
    private String NAME;        //姓名
    private String RIGHTS;        //权限
    private String ROLE_ID;        //角色id
    private String LAST_LOGIN;    //最后登录时间
    private String IP;            //用户登录ip地址
    private String STATUS;        //状态
    private String SKIN;        //皮肤
    private String department_id;        //部门id
    private String corpId;
    private String PHONE;

    public DataskyUserEntity(String USERID, String USERNAME, String PASSWORD, String NAME, String RIGHTS, String ROLE_ID, String LAST_LOGIN, String IP, String STATUS, String SKIN, String department_id, String corpId, String PHONE) {
        this.USERID = USERID;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
        this.NAME = NAME;
        this.RIGHTS = RIGHTS;
        this.ROLE_ID = ROLE_ID;
        this.LAST_LOGIN = LAST_LOGIN;
        this.IP = IP;
        this.STATUS = STATUS;
        this.SKIN = SKIN;
        this.department_id = department_id;
        this.corpId = corpId;
        this.PHONE = PHONE;
    }

    public DataskyUserEntity() {
    }

    public String getUSERID() {
        return USERID;
    }

    public void setUSERID(String USERID) {
        this.USERID = USERID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getRIGHTS() {
        return RIGHTS;
    }

    public void setRIGHTS(String RIGHTS) {
        this.RIGHTS = RIGHTS;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID;
    }

    public String getLAST_LOGIN() {
        return LAST_LOGIN;
    }

    public void setLAST_LOGIN(String LAST_LOGIN) {
        this.LAST_LOGIN = LAST_LOGIN;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getSKIN() {
        return SKIN;
    }

    public void setSKIN(String SKIN) {
        this.SKIN = SKIN;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    @Override
    public String toString() {
        return "DataskyUserEntity{" +
                "USERID='" + USERID + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", PASSWORD='" + PASSWORD + '\'' +
                ", NAME='" + NAME + '\'' +
                ", RIGHTS='" + RIGHTS + '\'' +
                ", ROLE_ID='" + ROLE_ID + '\'' +
                ", LAST_LOGIN='" + LAST_LOGIN + '\'' +
                ", IP='" + IP + '\'' +
                ", STATUS='" + STATUS + '\'' +
                ", SKIN='" + SKIN + '\'' +
                ", department_id='" + department_id + '\'' +
                ", corpId='" + corpId + '\'' +
                ", PHONE='" + PHONE + '\'' +
                '}';
    }
}