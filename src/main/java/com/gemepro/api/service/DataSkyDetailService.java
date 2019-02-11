package com.gemepro.api.service;


import com.gemepro.api.entity.DataskyDetailEntity;
import com.gemepro.api.entity.DataskyHeadEntity;
import com.gemepro.api.vo.DataSkyDistributionVo;
import com.gemepro.api.vo.DataSkyHeadVo;
import com.gemepro.api.vo.DataSkyVo;

import java.text.ParseException;
import java.util.List;

public interface DataSkyDetailService {

    /**
     * 插入探针返回的data数据
     *
     * @param dataSkyDetailEntity
     * @param dataSkyHeadEntity
     * @throws ParseException
     */
    void dataSkyDetailSave(DataskyDetailEntity[] dataSkyDetailEntity, DataskyHeadEntity dataSkyHeadEntity) throws ParseException, InterruptedException;


    int queryByIdCount(Long userId);

    List<DataskyDetailEntity> queryByTime(Long userId);

    List<DataskyDetailEntity> queryByIdTime2(Long userId, String beginTime);

    /**
     * 今日老客户数
     *
     * @return
     */
    int queryToOldCus(Long userId);

    /**
     * 昨日老客户数
     *
     * @return
     */
    int queryYesOldCus(Long userId, String beginTime);

    /**
     * 昨日客流量
     *
     * @param userId
     * @return
     */
    List<DataSkyHeadVo> queryDayCus(Long userId);

    /**
     * 非今日客流量
     *
     * @param userId
     * @param beginTime
     * @return
     */
    List<DataSkyHeadVo> queryNoDayCus(Long userId, String beginTime);

    List<DataSkyHeadVo> queryYearCus(Long userId, String beginTime);

    List<DataSkyHeadVo> queryMonthCus(Long userId, String beginTime);

    /**
     * 当月客流量之----今天客流
     *
     * @param userId
     * @return
     */
    DataSkyHeadVo queryMonthToCus(Long userId);

    /**
     * 老客户数/新客户数
     *
     * @param userId
     * @param beginTime
     * @return
     */
    List<DataSkyHeadVo> queryDataDayCus(Long userId, String beginTime);

    /**
     * 老客户数/新客户数
     *
     * @param userId
     * @param beginTime
     * @return
     */
    List<DataSkyHeadVo> queryDataMonthCus(Long userId, String beginTime);

    /**
     * 老客户数/新客户数
     *
     * @param userId
     * @param beginTime
     * @return
     */
    List<DataSkyHeadVo> queryDataYearCus(Long userId, String beginTime);

    /**
     * 累计驻店时长
     *
     * @param userId
     * @param beginTime
     * @return
     */
    List<DataSkyHeadVo> queryDataDayTimeLength(Long userId, String beginTime);

    List<DataSkyHeadVo> queryDataMonthTimeLength(Long userId, String beginTime);

    List<DataSkyHeadVo> queryDataYearTimeLength(Long userId, String beginTime);


    /**
     * 驻店时长对比
     *
     * @param userId
     * @param beginTime
     * @return
     */
    List<DataSkyHeadVo> queryTimeLengthContrast(Long userId, String beginTime);


    /**
     * 客户客流平均驻店时长
     *
     * @param userId
     * @param beginTime
     * @return
     */
    List<DataSkyHeadVo> queryYearAverageTimeLength(Long userId, String beginTime);

    List<DataSkyHeadVo> queryMonthAverageTimeLength(Long userId, String beginTime);

    List<DataSkyHeadVo> queryDayAverageTimeLength(Long userId, String beginTime);

    /**
     * 品牌统计 --进店客户数
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DataSkyVo> queryBrandInCus(Long userId, String beginTime, String endTime);

    /**
     * 品牌统计--累计驻店时长
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DataSkyVo> queryBrandTimeLength(Long userId, String beginTime, String endTime);

    /**
     * 客户平均驻店时长
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DataSkyVo> queryBrandAverageTimeLength(Long userId, String beginTime, String endTime);


    /**
     * 驻店时长分布---10分钟精度--5分钟精度
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    DataSkyDistributionVo queryTimeLengthDistributionBig(Long userId, String beginTime, String endTime);

    DataSkyDistributionVo queryTimeLengthDistributionSmall(Long userId, String beginTime, String endTime);


}