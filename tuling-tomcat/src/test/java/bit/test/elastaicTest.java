package bit.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.jackson.core.JsonProcessingException;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;


public class elastaicTest {

	TransportClient transportClient;
	//索引库名
	String index = "dn_test";
	//类型名称
	String type = "stu";
	
	@Before
	public void before()
	{
		/**
		 * 1:通过 setting对象来指定集群配置信息
		 */
		Settings setting = ImmutableSettings.settingsBuilder()
			.put("cluster.name", "cbt_es")//指定集群名称
//			.put("client.transport.sniff", true)//启动嗅探功能
			.build();
		
		/**
		 * 2：创建客户端
		 * 通过setting来创建，若不指定则默认链接的集群名为elasticsearch
		 * 链接使用tcp协议即9300
		 */
		transportClient = new TransportClient(setting);                        
		TransportAddress transportAddress = new InetSocketTransportAddress("123.56.21.219", 9611);
		transportClient.addTransportAddresses(transportAddress);
		
		/**
		 * 3：查看集群信息
		 * 注意我的集群结构是：
		 *   131的elasticsearch.yml中指定为主节点不能存储数据，
		 *   128的elasticsearch.yml中指定不为主节点只能存储数据。
		 * 所有控制台只打印了192.168.79.128,只能获取数据节点
		 * 
		 */
	    ImmutableList<DiscoveryNode> connectedNodes = transportClient.connectedNodes();
	    for(DiscoveryNode node : connectedNodes)
	    {
	    	System.out.println(node.getHostAddress());
	    }
	    
	}
	
	/**
	 * 通过prepareGet方法获取指定文档信息
	 */
	@Test
	public void testGet() {
		GetResponse getResponse = transportClient.prepareGet(index, type, "1").get();
		System.out.println(getResponse.getSourceAsString());
	}
	
	/**
	 * prepareUpdate更新索引库中文档，如果文档不存在则会报错
	 * @throws IOException
	 * 
	 */
	@Test
	public void testUpdate() throws IOException
	{
		XContentBuilder source = XContentFactory.jsonBuilder()
			.startObject()
			.field("name", "will")
			.endObject();
		
		UpdateResponse updateResponse = transportClient
				.prepareUpdate(index, type, "6").setDoc(source).get();
		
		System.out.println(updateResponse.getVersion());
	}

	/**
	 * 通过prepareIndex增加文档，参数为json字符串
	 */
	@Test
	public void testIndexJson()
	{
		String source = "{\"name\":\"will\",\"age\":18}";
		IndexResponse indexResponse = transportClient
				.prepareIndex(index, type).setSource(source).get();
		System.out.println(indexResponse.getVersion());
	}
	
	/**
	 * 通过prepareIndex增加文档，参数为Map<String,Object>
	 */
	@Test
	public void testIndexMap()
	{
		Map<String, Object> source = new HashMap<String, Object>(2);
		source.put("name", "Alice");
		source.put("age", 16);
		IndexResponse indexResponse = transportClient
				.prepareIndex(index, type, "4").setSource(source).get();
		System.out.println(indexResponse.getVersion());
	}
	
	/**
	 * 通过prepareIndex增加文档，参数为javaBean
	 * 
	 * @throws ElasticsearchException
	 * @throws JsonProcessingException
	 */
	@Test
	public void testIndexBean() throws ElasticsearchException
	{
		
		Student stu = new Student();
		stu.setName("Fresh");
		stu.setAge(22);
		String v = JSON.toJSONString(stu);
		IndexResponse indexResponse = transportClient.
				prepareIndex(index, type, "5").setSource(v).get();
		System.out.println(indexResponse.getVersion());
	}
	
	/**
	 * 通过prepareIndex增加文档，参数为XContentBuilder
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@Test
	public void testIndexXContentBuilder() throws IOException, InterruptedException, ExecutionException
	{
		XContentBuilder builder = XContentFactory.jsonBuilder()
				.startObject()
				.field("name", "Avivi")
				.field("age", 30)
				.endObject();
		IndexResponse indexResponse = transportClient
				.prepareIndex(index, type, "6")
				.setSource(builder)
				.execute().get();
		//.execute().get();和get()效果一样
		System.out.println(indexResponse.getVersion());
	}
	
	/**
	 * 通过prepareDelete删除文档
	 * 
	 */
	@Test
	public void testDelete()
	{
		String id = "9";
		DeleteResponse deleteResponse = transportClient.prepareDelete(index,
				type, id).get();
		
		System.out.println(deleteResponse.getVersion());
		
		//删除所有记录
		transportClient.prepareDeleteByQuery(index).setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery()).get();
	}
	
	/**
	 * 删除索引库，不可逆慎用
	 */
	@Test
	public void testDeleteeIndex()
	{
		transportClient.admin().indices().prepareDelete("shb01","shb02").get();
	}
	
	/**
	 * 求索引库文档总数
	 */
	@Test
	public void testCount()
	{
		long count = transportClient.prepareCount(index).get().getCount();
		System.out.println(count);
	}
	
	/**
	 * 通过prepareBulk执行批处理
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testBulk() throws IOException
	{
		//1:生成bulk
		BulkRequestBuilder bulk = transportClient.prepareBulk();
		
		//2:新增
		IndexRequest add = new IndexRequest(index, type, "10");
		add.source(XContentFactory.jsonBuilder()
					.startObject()
					.field("name", "Henrry").field("age", 30)
					.endObject());
		
		//3:删除
		DeleteRequest del = new DeleteRequest(index, type, "1");
		
		//4:修改
		XContentBuilder source = XContentFactory.jsonBuilder().startObject().field("name", "jack_1").field("age", 19).endObject();
		UpdateRequest update = new UpdateRequest(index, type, "2");
		update.doc(source);
		
		bulk.add(del);
		bulk.add(add);
		bulk.add(update);
		//5:执行批处理
		BulkResponse bulkResponse = bulk.get();
		if(bulkResponse.hasFailures())
		{
			BulkItemResponse[] items = bulkResponse.getItems();
			for(BulkItemResponse item : items)
			{
				System.out.println(item.getFailureMessage());
			}
		}
		else
		{
			System.out.println("全部执行成功！");
		}
	}
	
	/**
	 * 通过prepareSearch查询索引库
	 * setQuery(QueryBuilders.matchQuery("name", "jack"))
	 * setSearchType(SearchType.QUERY_THEN_FETCH)
	 * 
	 */
	@Test
	public void testSearch()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index)
				.setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
				//.setQuery(QueryBuilders.matchQuery("name", "tom").operator(Operator.AND)) //根据tom分词查询name,默认or
				//.setQuery(QueryBuilders.multiMatchQuery("tom", "name", "age")) //指定查询的字段
				//.setQuery(QueryBuilders.queryString("name:to* AND age:[0 TO 19]")) //根据条件查询,支持通配符大于等于0小于等于19
				//.setQuery(QueryBuilders.termQuery("name", "tom"))//查询时不分词
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.setFrom(0).setSize(10)//分页
				.addSort("age", SortOrder.DESC)//排序
				.get();
		
		SearchHits hits = searchResponse.getHits();
		long total = hits.getTotalHits();
		System.out.println(total);
		SearchHit[] searchHits = hits.hits();
		for(SearchHit s : searchHits)
		{
			System.out.println(s.getSourceAsString());
		}
	}
	
	/**
	 * 多索引，多类型查询
	 * timeout
	 */
	@Test
	public void testSearchsAndTimeout()
	{
		SearchResponse searchResponse = transportClient.prepareSearch("shb01","shb02").setTypes("stu","tea")
			.setQuery(QueryBuilders.matchAllQuery())
			.setSearchType(SearchType.QUERY_THEN_FETCH)
			.setTimeout("3")
		    .get();
		
		SearchHits hits = searchResponse.getHits();
        long totalHits = hits.getTotalHits();
        System.out.println(totalHits);
        SearchHit[] hits2 = hits.getHits();
        for(SearchHit h : hits2)
        {
        	System.out.println(h.getSourceAsString());
        }
	}
	
	/**
	 * 过滤，
	 * lt 小于
	 * gt 大于
	 * lte 小于等于
	 * gte 大于等于
	 * 
	 */
	@Test
	public void testFilter()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index)
				.setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery()) //查询所有
				.setSearchType(SearchType.QUERY_THEN_FETCH)
//				.setPostFilter(FilterBuilders.rangeFilter("age").from(0).to(19)
//						.includeLower(true).includeUpper(true))
				.setPostFilter(FilterBuilders.rangeFilter("age").gte(18).lte(22))
				.setExplain(true) //explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
				.get();
	
		
		SearchHits hits = searchResponse.getHits();
		long total = hits.getTotalHits();
		System.out.println(total);
		SearchHit[] searchHits = hits.hits();
		for(SearchHit s : searchHits)
		{
			System.out.println(s.getSourceAsString());
		}
	}
	
	/**
	 * 高亮
	 */
	@Test
	public void testHighLight()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index)
				.setTypes(type)
				//.setQuery(QueryBuilders.matchQuery("name", "Fresh")) //查询所有
				.setQuery(QueryBuilders.queryString("name:F*"))
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.addHighlightedField("name")
				.setHighlighterPreTags("<font color='red'>")
				.setHighlighterPostTags("</font>")
				.get();
	
		
		SearchHits hits = searchResponse.getHits();
		System.out.println("sum:" + hits.getTotalHits());
		
		SearchHit[] hits2 = hits.getHits();
		for(SearchHit s : hits2)
		{
			Map<String, HighlightField> highlightFields = s.getHighlightFields();
			HighlightField highlightField = highlightFields.get("name");
			if(null != highlightField)
			{
				Text[] fragments = highlightField.fragments();
				System.out.println(fragments[0]);
			}
			System.out.println(s.getSourceAsString());
		}
	}
	
	/**
	 * 分组
	 */
	@Test
	public void testGroupBy()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index).setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery())
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.addAggregation(AggregationBuilders.terms("group_age")
						.field("age").size(0))//根据age分组，默认返回10，size(0)也是10
				.get();
		
		Terms terms = searchResponse.getAggregations().get("group_age");
		List<Bucket> buckets = terms.getBuckets();
		for(Bucket bt : buckets)
		{
			System.out.println(bt.getKey() + " " + bt.getDocCount());
		}
	}
	
	/**
	 * 聚合函数,本例之编写了sum，其他的聚合函数也可以实现
	 * 
	 */
	@Test
	public void testMethod()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index).setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery())
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.addAggregation(AggregationBuilders.terms("group_name").field("name")
						.subAggregation(AggregationBuilders.sum("sum_age").field("age")))
				.get();
		
		Terms terms = searchResponse.getAggregations().get("group_name");
		List<Bucket> buckets = terms.getBuckets();
		for(Bucket bt : buckets)
		{
			Sum sum = bt.getAggregations().get("sum_age");
			System.out.println(bt.getKey() + "  " + bt.getDocCount() + " "+ sum.getValue());
		}
		
	}
	
	
public	static class Student{
		String name;
		int age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		
	}
}
