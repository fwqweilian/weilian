package com.gemepro.api.controller.admin;

import com.gemepro.api.annotation.LoginUser;
import com.gemepro.api.entity.DataskyEquipmentEntity;
import com.gemepro.api.entity.DataskyUserEntity;
import com.gemepro.api.service.DataSkyTokenService;
import com.gemepro.api.service.DataskyEquipmentService;
import com.gemepro.common.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/admin/equipment")
@Api(description = "设备管理接口")
public class DataSkyEquipmentController {

    @Autowired
    private DataskyEquipmentService dataskyEquipmentService;

    @Autowired
    private DataSkyTokenService tokenService;

    @GetMapping("/list")
    @ApiOperation(value = "设备列表", notes = "设备列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "long", name = "userid", value = "店铺id", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "String", name = "search", value = "搜索关键字")
    })
    public R list(@LoginUser DataskyUserEntity user, Long userid, String search) {
        if (search == null) {
            search = "";
        }
        List<DataskyEquipmentEntity> list = dataskyEquipmentService.queryEquipmentList(userid, search);
        return R.ok("设备列表获取成功").put(list);
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增设备", notes = "新增设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "body", dataType = " DataskyEquipmentEntity", name = "bean", value = "提交内容", required = true)
    })
    public R create(@LoginUser DataskyUserEntity user, @RequestBody DataskyEquipmentEntity bean) {
        if (bean.getUserId() == null) {
            return R.error("绑定店铺id不能为空");
        }
        if (bean.getEquipmentSn() == null) {
            return R.error("设备序SN号不能为空");
        }
        if (bean.getEquipmentId() == null) {
            return R.error("设备id不能为空");
        }
        if (bean.getEquipmentName() == null) {
            return R.error("设备名称不能为空");
        }
        if (dataskyEquipmentService.queryUserByTel(bean.getEquipmentSn()) != null) {
            return R.error("该设备SN号已存在");
        }
        //绑定的是店铺的id
        bean.setCreateId(bean.getUserId());
        bean.setCreateTime(new Date());
        bean.setEquipmentState(1);
        dataskyEquipmentService.save(bean);
        return R.ok("新增设备成功").put(bean);
    }

    @PostMapping("/update")
    @ApiOperation(value = "编辑设备", notes = "编辑设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "body", dataType = " DataskyEquipmentEntity", name = "bean", value = "提交内容", required = true)
    })
    public R update(@LoginUser DataskyUserEntity user, @RequestBody DataskyEquipmentEntity bean) {
        if (bean.getUserId() == null) {
            return R.error("绑定店铺id不能为空");
        }
        if (bean.getEquipmentName() == null) {
            return R.error("设备名称不能为空");
        }
        if (bean.getEquipmentSn() == null) {
            return R.error("设备SN码不能为空");
        }
        bean.setEquipmentState(1);
        dataskyEquipmentService.update(bean);
        return R.ok("编辑设备成功").put(bean);
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除设备", notes = "删除设备")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "equipmentids", value = "删除设备的id", required = true)
    })
    public R del(@LoginUser DataskyUserEntity user, @RequestBody List<Long> equipmentids) {
        if (equipmentids == null) {
            return R.error("删除设备的id不能为空");
        }
        dataskyEquipmentService.delete(equipmentids);
        return R.ok("删除设备成功");
    }


}
