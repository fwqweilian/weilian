package com.gemepro.api.controller.admin;

import com.gemepro.api.annotation.AuthIgnore;
import com.gemepro.api.annotation.LoginUser;
import com.gemepro.api.entity.DataskyUserEntity;
import com.gemepro.api.service.DataSkyTokenService;
import com.gemepro.api.service.DataskyUserService;
import com.gemepro.api.vo.DataSkyUserEquipmentVo;
import com.gemepro.common.utils.R;
import com.gemepro.common.utils.SmsUtil;
import com.gemepro.common.validator.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin/user")
@Api(description = "店铺管理接口")
public class DataSkyUserController {

    @Autowired
    private DataskyUserService dataskyUserService;

    @Autowired
    private DataSkyTokenService tokenService;

    @AuthIgnore
    @GetMapping("/login")
    @ApiOperation(value = "登录", notes = "登录说明")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "username", value = "用户名", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "password", value = "密码", required = true)
    })
    public R login(String username, String password) {
        Assert.isBlank(username, "用户名不能为空");
        Assert.isBlank(password, "密码不能为空");
        //用户登录
        DataskyUserEntity user = dataskyUserService.adminLogin(username, DigestUtils.md5DigestAsHex(password.getBytes()));
        //生成token
        if (user == null) {
            return R.error("用户名或密码错误");
        }
        Map<String, Object> map = tokenService.createToken(user.getUSERID(), 1);
        map.put("user", user);
        return R.ok("登陆成功").put(map);
    }

    @GetMapping("/list")
    @ApiOperation(value = "店铺列表", notes = "店铺列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "search", value = "搜索关键字")
    })
    public R list(@LoginUser DataskyUserEntity user, String search) {
        if (search == null) {
            search = "";
        }
        List<DataSkyUserEquipmentVo> list = dataskyUserService.queryUserList(user.getUSERID(), search);
        return R.ok("店铺列表获取成功").put(list);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "获取店铺", notes = "获取店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "id", value = "店铺id", required = true)
    })
    public R detail(@LoginUser DataskyUserEntity user, String id) {
        if (id == null) {
            return R.error("店铺id不能为空");
        }
        DataskyUserEntity dataskyUserEntity = dataskyUserService.queryObject(id);
        if (dataskyUserEntity == null) {
            return R.error("店铺不存在");
        }
        //不显示密码
        dataskyUserEntity.setPASSWORD(null);
        return R.ok("店铺获取成功").put(dataskyUserEntity);
    }


    @GetMapping("/resetPass")
    @ApiOperation(value = "重置密码", notes = "重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "userid", value = "重置密码店铺的id", required = true)
    })
    public R resetPass(@LoginUser DataskyUserEntity user, String userid) {
        if (userid == null) {
            return R.error("店铺的id不能为空");
        }
        DataskyUserEntity dataskyUserEntity = new DataskyUserEntity();
//        dataskyUserEntity.getUSER_ID(userid);
        dataskyUserEntity.setPASSWORD("123456");
        dataskyUserEntity.setPASSWORD(DigestUtils.md5DigestAsHex(dataskyUserEntity.getPASSWORD().getBytes()));
        dataskyUserService.update(dataskyUserEntity);
        return R.ok("重置密码成功");
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除店铺", notes = "删除店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "userids", value = "删除店铺的id", required = true)
    })
    public R del(@LoginUser DataskyUserEntity user, @RequestBody List<Long> userids) {
        if (userids == null) {
            return R.error("删除店铺的id不能为空");
        }
        dataskyUserService.delete(userids);
        return R.ok("删除店铺成功");
    }


    @PostMapping("/verifSms")
    @ApiOperation(value = "短信验证码", notes = "短信验证码说明")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "body", dataType = "DataskyUserEntity", name = "bean", value = "提交内容", required = true)
    })
    public R verifSms(@LoginUser DataskyUserEntity user, @RequestBody DataskyUserEntity bean) {
//        if (dataskyUserService.queryUserByTel(bean.getMobile()) != null) {
//            return R.error("该手机号已被注册");
//        }
//        //clientName App名称
//        String verifCode = SmsUtil.sendVerificationCode(bean.getMobile(), "");
//        return R.ok(verifCode);
        return null;
    }

}
