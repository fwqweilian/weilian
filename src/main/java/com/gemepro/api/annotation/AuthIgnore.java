package com.gemepro.api.annotation;

import java.lang.annotation.*;

/**
 * api接口，忽略Token验证，使用的地方并不多，一般是登录之类的操作
 * @author fwq
 * @email 1576034877@qq.com
 * @date 2017-03-23 15:44
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthIgnore {

}
