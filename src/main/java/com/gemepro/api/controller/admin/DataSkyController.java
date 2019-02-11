package com.gemepro.api.controller.admin;


import com.alibaba.fastjson.JSON;
import com.gemepro.api.annotation.AuthIgnore;
import com.gemepro.api.annotation.LoginUser;
import com.gemepro.api.entity.DataskyDetailEntity;
import com.gemepro.api.entity.DataskyHeadEntity;
import com.gemepro.api.entity.DataskyUserEntity;
import com.gemepro.api.service.DataSkyDetailService;
import com.gemepro.api.service.DataSkyHeadService;
import com.gemepro.api.service.DataskyUserService;
import com.gemepro.api.vo.DataSkyDistributionVo;
import com.gemepro.api.vo.DataSkyHeadVo;
import com.gemepro.api.vo.DataSkyVo;
import com.gemepro.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/api/datasky")
@Api(description = "数据统计")
public class DataSkyController {

    @Autowired
    private DataSkyHeadService dataSkyHeadService;
    @Autowired
    private DataSkyDetailService dataSkyDetailService;
    @Autowired
    private DataskyUserService dataskyUserService;

    @AuthIgnore
    @PostMapping("/datarecieve")
    @ApiOperation(value = "探针数据接收", notes = "探针数据接收")
    public void dataRecieve(HttpServletRequest request) throws Exception {
        Map requestParams = request.getParameterMap();
        String[] values = (String[]) requestParams.get("data");
        String valueStr = values[0].replace("&quot;", "\"");
        JSON jO = JSON.parseObject(valueStr);
        DataskyHeadEntity dataSkyHeadEntity = JSON.toJavaObject(jO, DataskyHeadEntity.class);

        //上报时间
        dataSkyHeadEntity.setDatatime(new Date());
        //获取json里面的data信息
        DataskyDetailEntity[] dataSkyDetailEntity = dataSkyHeadEntity.getData();

        //将探针返回的数据保存到数据库中两张表
        dataSkyHeadService.dataSkyHeanSave(dataSkyHeadEntity);
        dataSkyDetailService.dataSkyDetailSave(dataSkyDetailEntity, dataSkyHeadEntity);
    }

    //////////////**********************************//////////////////////////////////
    ///////////////////////////概况//////////////////////////////////////////////////
    /////////////**********************************//////////////////////////////////

    /**
     * 直接获取上报的数组的长度，不获取数据库的数据
     *
     * @param user
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/getCPF")
    @ApiOperation(value = "当前客流人数", notes = "当前客流人数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getCPF(@LoginUser DataskyUserEntity user, Long userId) throws Exception {
        List<DataskyDetailEntity> list = dataSkyDetailService.queryByTime(userId);
        Set<DataskyDetailEntity> set = new HashSet<>();
        if (list != null) {
            set.addAll(list);
        }
        return R.ok("当前客流人数").put(set.size());
    }

    /**
     * 是每一家店铺的客户并不是某一台设备扫描到的客户数
     * 驻店时长：同一个mac扫描到的次数*扫描频率
     *
     * @param beginTime
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping("/getTNOC")
    @ApiOperation(value = "客户数(包括新客户和老客户)、累计驻店时长、客户/客流平均驻店时长", notes = "客户数(包括新客户和老客户)、累计驻店时长、客户/客流平均驻店时长")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getTNOC(@LoginUser DataskyUserEntity user, String beginTime, Long userId) throws Exception {
        //获取开始和结束时间
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(beginTime);
        Map<String, Object> params = new HashMap<>(6);
        List<DataskyDetailEntity> list = new ArrayList<>();
        int customer = 1;
        int oldCustomer = 0;
        Set<DataskyDetailEntity> set = new HashSet<>();
        if (isNowDate(date)) {
            list = dataSkyDetailService.queryByTime(userId);
            oldCustomer = dataSkyDetailService.queryToOldCus(userId);
        } else {
            list = dataSkyDetailService.queryByIdTime2(userId, beginTime);
            oldCustomer = dataSkyDetailService.queryYesOldCus(userId, beginTime);
        }
        if (list != null) {
            set.addAll(list);
            customer = set.size();
        }
        params.put("customer", customer);
        params.put("oldCustomer", oldCustomer);
        params.put("newCustomer", (customer - oldCustomer));
        assert list != null;
        Double whenlong=0.0;
//        Double whenlong = list.size() * (dataskyUserService.queryObject(userId).getFrequency());
        params.put("whenlong", whenlong);
        params.put("passengerFloWwhenlong", (whenlong / customer));
        params.put("customerwhenlong", (whenlong / customer));
        return R.ok("客户数(包括新客户和老客户)、累计驻店时长、客户/客流平均驻店时长").put(params);
    }

    @GetMapping("/getPF")
    @ApiOperation(value = "客流量", notes = "客流量")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getPF(@LoginUser DataskyUserEntity user, String beginTime, Long userId) throws Exception {
        List<DataSkyHeadVo> dataSkyHeadVoList = null;
        String[] times = beginTime.trim().split("-");
        int timeLength = times.length;
        if (timeLength == 1) {
            dataSkyHeadVoList = dataSkyDetailService.queryYearCus(userId, beginTime);
        } else if (timeLength == 2) {
            Date date = new SimpleDateFormat("yyyy-MM").parse(beginTime);
            if (isNowDateMo(date)) {
                //当前月份，需要加上今天的数据
                dataSkyHeadVoList = dataSkyDetailService.queryMonthCus(userId, beginTime);
                DataSkyHeadVo headVo = dataSkyDetailService.queryMonthToCus(userId);
                if (headVo != null) {
                    dataSkyHeadVoList.add(headVo);
                }
            } else {
                dataSkyHeadVoList = dataSkyDetailService.queryMonthCus(userId, beginTime);
            }
        } else if (timeLength == 3) {
            if (isNowDate(new SimpleDateFormat("yyyy-MM-dd").parse(beginTime))) {
                dataSkyHeadVoList = dataSkyDetailService.queryDayCus(userId);
            } else {
                dataSkyHeadVoList = dataSkyDetailService.queryNoDayCus(userId, beginTime);
            }
        }
        return R.ok("客流量").put(dataSkyHeadVoList);
    }

    //////////////**********************************//////////////////////////////////
    ///////////////////////////数据统计//////////////////////////////////////////////
    /////////////**********************************//////////////////////////////////

    @GetMapping("/getTNOOCITS")
    @ApiOperation(value = "进店老/新客户数", notes = "进店老/新客户数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getTNOOCITS(@LoginUser DataskyUserEntity user, String beginTime, Long userId) throws Exception {
        int timeLength = beginTime.trim().split("-").length;
        List<DataSkyHeadVo> dataSkyHeadVoList = null;
        if (timeLength == 1) {
            dataSkyHeadVoList = dataSkyDetailService.queryDataYearCus(userId, beginTime);
        } else if (timeLength == 2) {
            dataSkyHeadVoList = dataSkyDetailService.queryDataMonthCus(userId, beginTime);
        } else if (timeLength == 3) {
            dataSkyHeadVoList = dataSkyDetailService.queryDataDayCus(userId, beginTime);
        }
        return R.ok("进店老/新客户数").put(dataSkyHeadVoList);
    }

    @GetMapping("/getATL")
    @ApiOperation(value = "累计驻店时长", notes = "累计驻店时长")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getATL(@LoginUser DataskyUserEntity user, String beginTime, Long userId) throws Exception {
        int timeLength = beginTime.trim().split("-").length;
        List<DataSkyHeadVo> dataSkyHeadVoList = null;
        if (timeLength == 1) {
            dataSkyHeadVoList = dataSkyDetailService.queryDataYearTimeLength(userId, beginTime);
        } else if (timeLength == 2) {
            dataSkyHeadVoList = dataSkyDetailService.queryDataMonthTimeLength(userId, beginTime);
        } else if (timeLength == 3) {
            dataSkyHeadVoList = dataSkyDetailService.queryDataDayTimeLength(userId, beginTime);
        }
        return R.ok("累计驻店时长").put(dataSkyHeadVoList);
    }

    @GetMapping("/getLDC")
    @ApiOperation(value = "驻店时长分布", notes = "驻店时长分布")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "endTime", value = "结束时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "accuracy", value = "精度", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getLDC(@LoginUser DataskyUserEntity user, String beginTime, Long userId, String endTime, String accuracy) throws ParseException {
        String a = "10";
        String[] intervalTime;
        DataSkyDistributionVo dataSkyDistributionVo = new DataSkyDistributionVo();
        if (a.equals(accuracy)) {
            intervalTime = new String[]{"0-10", "10-20", "20-30", "30-40", "40-50", "50-60", "60-70", "70-80", "80以上"};
            dataSkyDistributionVo = dataSkyDetailService.queryTimeLengthDistributionBig(userId, beginTime, endTime);
        } else {
            intervalTime = new String[]{"0-5", "5-10", "10-15", "15-20", "20-25", "25-30", "30以上"};
            dataSkyDistributionVo = dataSkyDetailService.queryTimeLengthDistributionSmall(userId, beginTime, endTime);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("X", intervalTime);
        map.put("Y", dataSkyDistributionVo);
        return R.ok("驻店时长分布").put(map);
    }

    /**
     * 驻店时长对比  Long contrast in store time
     *
     * @param beginTime 自然月
     * @param userId    商家ID
     * @return 返回驻店时长对比
     * @throws ParseException 上报错误信息
     */
    @GetMapping("/getLCST")
    @ApiOperation(value = "驻店时长对比", notes = "驻店时长对比")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "自然月", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getLCST(@LoginUser DataskyUserEntity user, String beginTime, Long userId) throws ParseException {
        List<DataSkyHeadVo> dataSkyHeadVoList = dataSkyDetailService.queryTimeLengthContrast(userId, beginTime);
        return R.ok("驻店时长对比").put(dataSkyHeadVoList);
    }

    @GetMapping("/getALCS")
    @ApiOperation(value = "客户/流平均驻店时长", notes = "客户/流平均驻店时长")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getALCS(@LoginUser DataskyUserEntity user, String beginTime, Long userId) throws ParseException {
        int timeLength = beginTime.trim().split("-").length;
        List<DataSkyHeadVo> dataSkyHeadVoList = null;
        if (timeLength == 1) {
            dataSkyHeadVoList = dataSkyDetailService.queryYearAverageTimeLength(userId, beginTime);
        } else if (timeLength == 2) {
            dataSkyHeadVoList = dataSkyDetailService.queryMonthAverageTimeLength(userId, beginTime);
        } else if (timeLength == 3) {
            dataSkyHeadVoList = dataSkyDetailService.queryDayAverageTimeLength(userId, beginTime);
        }
        return R.ok("客户平均驻店时长(单位：分钟)").put(dataSkyHeadVoList);
    }

    //////////////**********************************//////////////////////////////////
    ///////////////////////////品牌统计//////////////////////////////////////////////
    /////////////**********************************//////////////////////////////////

    @GetMapping("/getNOCIS")
    @ApiOperation(value = "品牌统计-进店客户数", notes = "品牌统计-进店客户数")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "endTime", value = "结束时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getNOCIS(@LoginUser DataskyUserEntity user, String beginTime, Long userId, String endTime) throws Exception {
        List<DataSkyVo> dataSkyVoList = dataSkyDetailService.queryBrandInCus(userId, beginTime, endTime);
        return R.ok("进店客户数").put(dataSkyVoList);
    }

    @GetMapping("/getBrandATL")
    @ApiOperation(value = "品牌统计-累计驻店时长", notes = "品牌统计-累计驻店时长")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "endTime", value = "结束时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getBrandATL(@LoginUser DataskyUserEntity user, String beginTime, String endTime, Long userId) throws Exception {
        List<DataSkyVo> dataSkyVoList = dataSkyDetailService.queryBrandTimeLength(userId, beginTime, endTime);
        return R.ok("累计驻店时长").put(dataSkyVoList);
    }

    @GetMapping("/getBrandALCS")
    @ApiOperation(value = "品牌统计-客户平均驻店时长", notes = "品牌统计-客户平均驻店时长")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "beginTime", value = "开始时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "endTime", value = "结束时间", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "userId", value = "商家ID", required = true)
    })
    public R getBrandALCS(@LoginUser DataskyUserEntity user, String beginTime, String endTime, Long userId) throws ParseException {
        List<DataSkyVo> dataSkyVoList = dataSkyDetailService.queryBrandAverageTimeLength(userId, beginTime, endTime);
        return R.ok("客户平均驻店时长").put(dataSkyVoList);
    }


    /**
     * 判断时间是不是今天
     *
     * @param date
     * @return 是返回true，不是返回false
     */
    private boolean isNowDate(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

    private static boolean isNowDateMo(Date date) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        //获取今天的日期
        String nowDay = sf.format(now);
        //对比的时间
        String day = sf.format(date);
        return day.equals(nowDay);
    }

}
