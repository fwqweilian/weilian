package com.gemepro.api.vo;


public class DataSkyHeadVo {


    /**
     * 客流--月
     */
    private int month;

    /**
     * 客流--天
     */
    private int day;

    /**
     * 客流--小时
     */
    private int hours;

    /**
     * 客流数
     */
    private Double num;

    /**
     * 老客户数
     */
    private int oldCus;

    /**
     * 新客户数
     */
    private int newCus;

    /**
     * 数据，为了方便
     */
    private int count;

    /**
     * 星期几
     */
    private String week;

    /**
     * 第几周
     */
    private String weeknum;

    public String getWeeknum() {
        return weeknum;
    }

    public void setWeeknum(String weeknum) {
        this.weeknum = weeknum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getOldCus() {
        return oldCus;
    }

    public void setOldCus(int oldCus) {
        this.oldCus = oldCus;
    }

    public int getNewCus() {
        return newCus;
    }

    public void setNewCus(int newCus) {
        this.newCus = newCus;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
