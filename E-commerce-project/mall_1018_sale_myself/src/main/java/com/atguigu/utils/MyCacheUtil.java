package com.atguigu.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class MyCacheUtil {
	/**
	 * 根据key获取redis中的值集合
	 * 
	 * @param key
	 *            redis中的key
	 * @param t
	 *            要封装的对象.class
	 * @return
	 */
	public static <T> List<T> getList(String key, Class<T> t) {
		// redis缓存检索,从连接池获取一条jedis
		List<T> list = new ArrayList<T>();
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
		} catch (Exception e) {
			e.getStackTrace();
			return null;
		}
		Set<String> zrange = jedis.zrange(key, 0, -1);
		Iterator<String> iterator = zrange.iterator();
		while (iterator.hasNext()) {
			String str = iterator.next();
			T sku = MyJsonUtil.json_to_object(str, t);
			list.add(sku);
		}
		return list;
	}

	/**
	 * 将数据库中的数据集合同步到redis
	 * 
	 * @param key
	 *            存入的key
	 * @param t
	 *            对象集合
	 */
	public static <T> void setKey(String key, List<T> t) {
		// 将检索结果同步到redis
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
			// 删除key，防止redis追加数据
			jedis.del(key);
			for (int i = 0; i < t.size(); i++) {
				// 向redis中插入数据
				jedis.zadd(key, i, MyJsonUtil.object_to_json(t.get(i)));
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	/**
	 * 根据传进来的keys生成动态keys
	 * 
	 * @param keys
	 *            出入的key数组
	 * @return 生成动态的keys
	 */
	public static String getInterKeys(String... keys) {
		Jedis jedis = null;
		try {
			jedis = JedisPoolUtils.getJedis();
			String interKeys = "combine";
			// 生车公动态key
			for (int i = 0; i < keys.length; i++) {
				interKeys = interKeys + "_" + keys[i];
			}
			// 判断key在redis中是否存在
			if (!jedis.exists(interKeys)) {
				// 将数组keys的值的交集方法key为interKeys的zset中，取出用interKeys取即可
				jedis.zinterstore(interKeys, keys);
			}
			return interKeys;
		} catch (Exception e) {
			e.getStackTrace();
		}

		return "";
	}
}
