package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.util.MyPropertiesUtil;

@Controller
public class KeysController {

	@RequestMapping("keywords")
	@ResponseBody
	public List<KEYWORDS_T_MALL_SKU> keywords(String keywords) throws Exception {
		List<KEYWORDS_T_MALL_SKU> list_sku = new ArrayList<>();
		// 1.获取solr服务器
		HttpSolrServer solrServer = new HttpSolrServer(MyPropertiesUtil.getMyProperty("solr.properties", "slor_url"));
		// 中间转换
		solrServer.setParser(new XMLResponseParser());
		// 查询solr中的数据
		// 获取查询对象
		SolrQuery solrQuery = new SolrQuery();
		// 设置查询条件
		solrQuery.setQuery("combine_item:"+keywords);
		// 设置查询的数量
		solrQuery.setRows(Integer.MAX_VALUE);
		// 获取查询对象
		QueryResponse query = solrServer.query(solrQuery);
		// 获取对象
		list_sku = query.getBeans(KEYWORDS_T_MALL_SKU.class);
		return list_sku;
	}
}
