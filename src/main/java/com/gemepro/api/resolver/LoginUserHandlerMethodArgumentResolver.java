package com.gemepro.api.resolver;

import com.gemepro.api.annotation.LoginUser;
import com.gemepro.api.entity.DataskyUserEntity;
import com.gemepro.api.interceptor.AuthorizationInterceptor;
import com.gemepro.api.service.DataskyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户,绝对的低耦合，无感操作
 *
 * @author fwq
 * @email 1576034877@qq.com
 * @date 2017-03-23 22:02
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private DataskyUserService dataskyUserService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return (parameter.getParameterType().isAssignableFrom(DataskyUserEntity.class)) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object object = request.getAttribute(AuthorizationInterceptor.LOGIN_USER_KEY, RequestAttributes.SCOPE_REQUEST);
        //获取登陆标识
        Object tag = request.getAttribute(AuthorizationInterceptor.TAG, RequestAttributes.SCOPE_REQUEST);

        DataskyUserEntity user = dataskyUserService.queryObject((String) object);
        return user;
    }
}
