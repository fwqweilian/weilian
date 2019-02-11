package com.gemepro.api.service.impl;

import com.gemepro.api.dao.DataskyDetailDao;
import com.gemepro.api.dao.DataskyToCusDao;
import com.gemepro.api.entity.DataskyDetailEntity;
import com.gemepro.api.entity.DataskyHeadEntity;
import com.gemepro.api.entity.DataskyToCusEntity;
import com.gemepro.api.service.DataSkyDetailService;
import com.gemepro.api.vo.DataSkyDistributionVo;
import com.gemepro.api.vo.DataSkyHeadVo;
import com.gemepro.api.vo.DataSkyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * @author fwq
 */
@Service("dataSkyDetailService")
@Configuration
@EnableScheduling
public class DataSkyDetailServiceImpl implements DataSkyDetailService {

    @Autowired
    private DataskyDetailDao dataSkyDetailDao;

    @Autowired
    private DataskyToCusDao dataSkyToCusDao;

    /**
     * 插入探针返回的data数据
     *
     * @param dataSkyDetailEntity
     * @param dataSkyHeadEntity
     * @throws ParseException
     */
    @Override
    public void dataSkyDetailSave(DataskyDetailEntity[] dataSkyDetailEntity, DataskyHeadEntity dataSkyHeadEntity) throws ParseException, InterruptedException {
        for (DataskyDetailEntity detailEntity : dataSkyDetailEntity) {
            //上报日期
            detailEntity.setDatatime(dataSkyHeadEntity.getDatatime());
            //探针设备id
            detailEntity.setDataskyid(dataSkyHeadEntity.getId());
            dataSkyDetailDao.save(detailEntity);
            //将实时数据保存到今天的数据表中，然后每天凌晨把今天的数据加入到历史表中,最后清空今天的数据表
            DataskyToCusEntity dataskyToCusEntity = new DataskyToCusEntity();
            dataskyToCusEntity.setMac(detailEntity.getMac());
            dataskyToCusEntity.setMacTime(detailEntity.getDatatime());
            dataskyToCusEntity.setDataskyid(detailEntity.getDataskyid());
            dataSkyToCusDao.save(dataskyToCusEntity);
        }
    }

    @Override
    public int queryByIdCount(Long userId) {
        return dataSkyDetailDao.queryByIdCount(userId);
    }

    @Override
    public List<DataskyDetailEntity> queryByTime(Long userId) {
        return dataSkyDetailDao.queryByTime(userId);
    }

    @Override
    public List<DataskyDetailEntity> queryByIdTime2(Long userId, String beginTime) {
        return dataSkyDetailDao.queryByIdTime2(userId, beginTime);
    }

    /**
     * 今日老客户数
     *
     * @return
     */
    @Override
    public int queryToOldCus(Long userId) {
        return dataSkyDetailDao.queryToOldCus(userId);
    }

    /**
     * 昨日老客户数
     *
     * @return
     */
    @Override
    public int queryYesOldCus(Long userId, String beginTime) {
        return dataSkyDetailDao.queryYesOldCus(userId, beginTime);
    }

    /**
     * 昨日客流量
     *
     * @param userId
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryDayCus(Long userId) {
        return dataSkyDetailDao.queryDayCus(userId);
    }

    /**
     * 非今日客流量
     *
     * @param userId
     * @param beginTime
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryNoDayCus(Long userId, String beginTime) {
        return dataSkyDetailDao.queryNoDayCus(userId, beginTime);
    }

    /**
     * 年客流量
     *
     * @param userId
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryYearCus(Long userId, String beginTime) {
        return dataSkyDetailDao.queryYearCus(userId, beginTime);
    }

    @Override
    public List<DataSkyHeadVo> queryMonthCus(Long userId, String beginTime) {
        return dataSkyDetailDao.queryMonthCus(userId, beginTime);
    }

    /**
     * 老客户数/新客户数
     *
     * @param userId
     * @param beginTime
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryDataDayCus(Long userId, String beginTime) {
        return dataSkyDetailDao.queryDataDayCus(userId, beginTime);
    }

    /**
     * 老客户数/新客户数
     *
     * @param userId
     * @param beginTime
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryDataMonthCus(Long userId, String beginTime) {
        return dataSkyDetailDao.queryDataMonthCus(userId, beginTime);
    }

    /**
     * 老客户数/新客户数
     *
     * @param userId
     * @param beginTime
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryDataYearCus(Long userId, String beginTime) {
        return dataSkyDetailDao.queryDataYearCus(userId, beginTime);
    }

    /**
     * 累计驻店时长
     *
     * @param userId
     * @param beginTime
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryDataDayTimeLength(Long userId, String beginTime) {
        return dataSkyDetailDao.queryDataDayTimeLength(userId, beginTime);
    }

    @Override
    public List<DataSkyHeadVo> queryDataMonthTimeLength(Long userId, String beginTime) {
        return dataSkyDetailDao.queryDataMonthTimeLength(userId, beginTime);
    }

    /**
     * 当月客流量之----今天客流
     *
     * @param userId
     * @return
     */
    @Override
    public DataSkyHeadVo queryMonthToCus(Long userId) {
        return dataSkyDetailDao.queryMonthToCus(userId);
    }

    @Override
    public List<DataSkyHeadVo> queryDataYearTimeLength(Long userId, String beginTime) {
        return dataSkyDetailDao.queryDataYearTimeLength(userId, beginTime);
    }


    /**
     * 驻店时长对比
     *
     * @param userId
     * @param beginTime
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryTimeLengthContrast(Long userId, String beginTime) {
        return dataSkyDetailDao.queryTimeLengthContrast(userId, beginTime);
    }

    /**
     * 客户客流平均驻店时长
     *
     * @param userId
     * @param beginTime
     * @return
     */
    @Override
    public List<DataSkyHeadVo> queryYearAverageTimeLength(Long userId, String beginTime) {
        return dataSkyDetailDao.queryYearAverageTimeLength(userId, beginTime);
    }

    @Override
    public List<DataSkyHeadVo> queryMonthAverageTimeLength(Long userId, String beginTime) {
        return dataSkyDetailDao.queryMonthAverageTimeLength(userId, beginTime);
    }

    @Override
    public List<DataSkyHeadVo> queryDayAverageTimeLength(Long userId, String beginTime) {
        return dataSkyDetailDao.queryDayAverageTimeLength(userId, beginTime);
    }

    /**
     * 品牌统计 --进店客户数
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public List<DataSkyVo> queryBrandInCus(Long userId, String beginTime, String endTime) {
        return dataSkyDetailDao.queryBrandInCus(userId, beginTime, endTime);
    }

    /**
     * 品牌统计--累计驻店时长
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public List<DataSkyVo> queryBrandTimeLength(Long userId, String beginTime, String endTime) {
        return dataSkyDetailDao.queryBrandTimeLength(userId, beginTime, endTime);
    }

    /**
     * 客户平均驻店时长
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public List<DataSkyVo> queryBrandAverageTimeLength(Long userId, String beginTime, String endTime) {
        return dataSkyDetailDao.queryBrandAverageTimeLength(userId, beginTime, endTime);
    }

    /**
     * 驻店时长分布---10分钟精度--5分钟精度
     *
     * @param userId
     * @param beginTime
     * @param endTime
     * @return
     */
    @Override
    public DataSkyDistributionVo queryTimeLengthDistributionBig(Long userId, String beginTime, String endTime) {
        return dataSkyDetailDao.queryTimeLengthDistributionBig(userId, beginTime, endTime);
    }

    @Override
    public DataSkyDistributionVo queryTimeLengthDistributionSmall(Long userId, String beginTime, String endTime) {
        return dataSkyDetailDao.queryTimeLengthDistributionSmall(userId, beginTime, endTime);
    }
}
