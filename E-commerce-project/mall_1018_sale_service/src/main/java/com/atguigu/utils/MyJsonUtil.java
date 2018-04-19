package com.atguigu.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import net.sf.json.JSONArray;

public class MyJsonUtil {
	/**
	 * 将Object转换为Json
	 * @param t
	 * @return
	 */
	public static <T> String object_to_json(T t) {
		Gson gson = new Gson();

		String json = gson.toJson(t);

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 将Json对象转换为Object
	 * @param json 
	 * @param t 类型
	 * @return
	 */
	public static <T> T json_to_object(String json, Class<T> t) {

		try {
			json = URLDecoder.decode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Gson gson = new Gson();

		T fromJson = gson.fromJson(json, t);

		return fromJson;
	}
	/**
	 * 将Json转换为List集合
	 * @param json
	 * @param t 要转换为集合的类型
	 * @return
	 */
	public static <T> List<T> json_to_list(String json, Class<T> t) {
		String decode = "";

		if (StringUtils.isBlank(json)) {
			return null;
		} else {
			try {
				decode = URLDecoder.decode(json, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			JSONArray fromObject2 = JSONArray.fromObject(decode);

			List<T> list_array = (List<T>) JSONArray.toCollection(fromObject2, t);

			return list_array;
		}

	}
	/**
	 * 将List集合转换为Json
	 * @param list
	 * @return
	 */
	public static <T> String list_to_json(List<T> list) {
		Gson gson = new Gson();
		String json = gson.toJson(list);

		try {
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return json;

	}
}
