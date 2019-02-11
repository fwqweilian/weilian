package com.gemepro.api.dao;

import com.gemepro.api.entity.DataskyDetailEntity;
import com.gemepro.api.vo.DataSkyDistributionVo;
import com.gemepro.api.vo.DataSkyHeadVo;
import com.gemepro.api.vo.DataSkyVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataskyDetailDao extends BaseDao<DataskyDetailEntity> {
    int queryByIdCount(@Param("userId") Long userId);

    List<DataskyDetailEntity> queryByTime(@Param("userId") Long userId);

    List<DataskyDetailEntity> queryByIdTime2(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    int queryToOldCus(@Param("userId") Long userId);

    int queryYesOldCus(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryDayCus(@Param("userId") Long userId);

    List<DataSkyHeadVo> queryNoDayCus(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryYearCus(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryMonthCus(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    DataSkyHeadVo queryMonthToCus(@Param("userId") Long userId);

    List<DataSkyHeadVo> queryDataDayCus(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryDataMonthCus(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryDataYearCus(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryDataDayTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryDataMonthTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryDataYearTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryTimeLengthContrast(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryYearAverageTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryMonthAverageTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyHeadVo> queryDayAverageTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime);

    List<DataSkyVo> queryBrandInCus(@Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    List<DataSkyVo> queryBrandTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    List<DataSkyVo> queryBrandAverageTimeLength(@Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    DataSkyDistributionVo queryTimeLengthDistributionBig(@Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    DataSkyDistributionVo queryTimeLengthDistributionSmall(@Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}