package com.gemepro.api.interceptor;


import com.alibaba.fastjson.JSON;
import com.gemepro.api.annotation.AuthIgnore;
import com.gemepro.api.entity.DataSkyTokenEntity;
import com.gemepro.api.service.DataSkyTokenService;
import com.gemepro.common.exception.RRException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 权限(Token)验证
 *
 * @author fwq
 * @email 1576034877@qq.com
 * @date 2017-03-23 15:38
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private DataSkyTokenService dataSkyTokenService;

    public static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";
    public static final String TAG = "TAG";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }

        //token为空
        if (StringUtils.isBlank(token)) {
            throw new RRException("token不能为空");
        }

        //查询token信息
        DataSkyTokenEntity tokenEntity = dataSkyTokenService.queryByToken(token);
        if (tokenEntity == null) {
            response.setContentType("application/json; charset=utf-8");
            Map<String, Object> params = new HashMap<>();

            params.put("code", 403);
            params.put("msg", "您的账号在另外一台机器登录过，请重新登录");

            response.getWriter().append(JSON.toJSONString(params));
            return false;
        }
        if (tokenEntity.getExpireTime().getTime() < System.currentTimeMillis()) {
            response.setContentType("application/json; charset=utf-8");
            Map<String, Object> params = new HashMap<>();

            params.put("code", 403);
            params.put("msg", "长时间没有登录，请重新登录");

            response.getWriter().append(JSON.toJSONString(params));
            return false;
        }
        //设置userId到request里，后续根据userId，获取用户信息
        request.setAttribute(LOGIN_USER_KEY, tokenEntity.getUserId());
        request.setAttribute(TAG, tokenEntity.getTag());
        return true;
    }
}
