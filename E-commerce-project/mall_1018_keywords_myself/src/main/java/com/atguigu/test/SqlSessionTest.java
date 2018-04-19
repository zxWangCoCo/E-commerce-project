package com.atguigu.test;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.factory.MySqlSessionFactory;
import com.atguigu.mapper.ClassMapper;
import com.atguigu.util.MyPropertiesUtil;
public class SqlSessionTest {

	public static void main(String[] args) throws Exception {
		SqlSessionFactory sqlSession = MySqlSessionFactory.getSqlSessionFB();
		ClassMapper mapper = sqlSession.openSession().getMapper(ClassMapper.class);
		List<KEYWORDS_T_MALL_SKU> list_sku = mapper.select_list_by_flbh2(28);
		//向Solr中插入数据
		//1.获取solr服务器
HttpSolrServer solrServer = 
	new HttpSolrServer(MyPropertiesUtil.getMyProperty("solr.properties", "slor_url"));
		//中间转换
		solrServer.setParser(new XMLResponseParser());
		//添加数据
		solrServer.addBeans(list_sku);	
		//提交数据
		solrServer.commit();
		
		//查询solr中的数据
			//获取查询对象
		 SolrQuery solrQuery = new SolrQuery();
		 	//设置查询条件
		 solrQuery.setQuery("combine_item:小明");
		 	//设置查询的数量
		 solrQuery.setRows(Integer.MAX_VALUE);
		 	//获取查询对象
		 QueryResponse query = solrServer.query(solrQuery);
		 	//获取对象
		 List<KEYWORDS_T_MALL_SKU> beans = query.getBeans(KEYWORDS_T_MALL_SKU.class);
		 System.out.println(beans);
	}
}
