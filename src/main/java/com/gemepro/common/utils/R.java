package com.gemepro.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据，封装，直接返回json数据
 * 
 * @author fwq
 * @email 1576034877@qq.com
 * @date 2017年10月27日 下午9:59:27
 */
public class R extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;

	public R() {
		put("code", 200);
	}

	public static R error() {
		return error(500, "未知异常，请联系管理员");
	}

	public static R error(String msg) {
		return error(500, msg);
	}

	public static R error(int code, String msg) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static R error(int code, String msg, Map<String,Object> map) {
		R r = new R();
		r.put("code", code);
		r.put("msg", msg);
		r.put("data",map);
		return r;
	}

	public static R ok(String msg) {
		R r = new R();
		r.put("msg", msg);
		return r;
	}

	public static R ok(Map<String, Object> map) {
		R r = new R();
		r.putAll(map);
		return r;
	}

	public static R ok() {
		return new R();
	}

	public R put(Object value) {
		super.put("data", value);
		return this;
	}

	public R put(String key, Object value) {
		super.put(key, value);
		return this;
	}

	public R putData(Object value, Map<String, Object> map) {
		super.put("data", value);
		super.put("paging",map);
		return this;
	}
}
