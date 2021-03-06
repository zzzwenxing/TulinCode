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
	//????????????
	String index = "dn_test";
	//????????????
	String type = "stu";
	
	@Before
	public void before()
	{
		/**
		 * 1:?????? setting?????????????????????????????????
		 */
		Settings setting = ImmutableSettings.settingsBuilder()
			.put("cluster.name", "cbt_es")//??????????????????
//			.put("client.transport.sniff", true)//??????????????????
			.build();
		
		/**
		 * 2??????????????????
		 * ??????setting??????????????????????????????????????????????????????elasticsearch
		 * ????????????tcp?????????9300
		 */
		transportClient = new TransportClient(setting);                        
		TransportAddress transportAddress = new InetSocketTransportAddress("123.56.21.219", 9611);
		transportClient.addTransportAddresses(transportAddress);
		
		/**
		 * 3?????????????????????
		 * ??????????????????????????????
		 *   131???elasticsearch.yml??????????????????????????????????????????
		 *   128???elasticsearch.yml?????????????????????????????????????????????
		 * ???????????????????????????192.168.79.128,????????????????????????
		 * 
		 */
	    ImmutableList<DiscoveryNode> connectedNodes = transportClient.connectedNodes();
	    for(DiscoveryNode node : connectedNodes)
	    {
	    	System.out.println(node.getHostAddress());
	    }
	    
	}
	
	/**
	 * ??????prepareGet??????????????????????????????
	 */
	@Test
	public void testGet() {
		GetResponse getResponse = transportClient.prepareGet(index, type, "1").get();
		System.out.println(getResponse.getSourceAsString());
	}
	
	/**
	 * prepareUpdate????????????????????????????????????????????????????????????
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
	 * ??????prepareIndex????????????????????????json?????????
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
	 * ??????prepareIndex????????????????????????Map<String,Object>
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
	 * ??????prepareIndex????????????????????????javaBean
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
	 * ??????prepareIndex????????????????????????XContentBuilder
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
		//.execute().get();???get()????????????
		System.out.println(indexResponse.getVersion());
	}
	
	/**
	 * ??????prepareDelete????????????
	 * 
	 */
	@Test
	public void testDelete()
	{
		String id = "9";
		DeleteResponse deleteResponse = transportClient.prepareDelete(index,
				type, id).get();
		
		System.out.println(deleteResponse.getVersion());
		
		//??????????????????
		transportClient.prepareDeleteByQuery(index).setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery()).get();
	}
	
	/**
	 * ?????????????????????????????????
	 */
	@Test
	public void testDeleteeIndex()
	{
		transportClient.admin().indices().prepareDelete("shb01","shb02").get();
	}
	
	/**
	 * ????????????????????????
	 */
	@Test
	public void testCount()
	{
		long count = transportClient.prepareCount(index).get().getCount();
		System.out.println(count);
	}
	
	/**
	 * ??????prepareBulk???????????????
	 * 
	 * @throws IOException 
	 */
	@Test
	public void testBulk() throws IOException
	{
		//1:??????bulk
		BulkRequestBuilder bulk = transportClient.prepareBulk();
		
		//2:??????
		IndexRequest add = new IndexRequest(index, type, "10");
		add.source(XContentFactory.jsonBuilder()
					.startObject()
					.field("name", "Henrry").field("age", 30)
					.endObject());
		
		//3:??????
		DeleteRequest del = new DeleteRequest(index, type, "1");
		
		//4:??????
		XContentBuilder source = XContentFactory.jsonBuilder().startObject().field("name", "jack_1").field("age", 19).endObject();
		UpdateRequest update = new UpdateRequest(index, type, "2");
		update.doc(source);
		
		bulk.add(del);
		bulk.add(add);
		bulk.add(update);
		//5:???????????????
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
			System.out.println("?????????????????????");
		}
	}
	
	/**
	 * ??????prepareSearch???????????????
	 * setQuery(QueryBuilders.matchQuery("name", "jack"))
	 * setSearchType(SearchType.QUERY_THEN_FETCH)
	 * 
	 */
	@Test
	public void testSearch()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index)
				.setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery()) //????????????
				//.setQuery(QueryBuilders.matchQuery("name", "tom").operator(Operator.AND)) //??????tom????????????name,??????or
				//.setQuery(QueryBuilders.multiMatchQuery("tom", "name", "age")) //?????????????????????
				//.setQuery(QueryBuilders.queryString("name:to* AND age:[0 TO 19]")) //??????????????????,???????????????????????????0????????????19
				//.setQuery(QueryBuilders.termQuery("name", "tom"))//??????????????????
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.setFrom(0).setSize(10)//??????
				.addSort("age", SortOrder.DESC)//??????
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
	 * ???????????????????????????
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
	 * ?????????
	 * lt ??????
	 * gt ??????
	 * lte ????????????
	 * gte ????????????
	 * 
	 */
	@Test
	public void testFilter()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index)
				.setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery()) //????????????
				.setSearchType(SearchType.QUERY_THEN_FETCH)
//				.setPostFilter(FilterBuilders.rangeFilter("age").from(0).to(19)
//						.includeLower(true).includeUpper(true))
				.setPostFilter(FilterBuilders.rangeFilter("age").gte(18).lte(22))
				.setExplain(true) //explain???true???????????????????????????????????????????????????????????????????????????
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
	 * ??????
	 */
	@Test
	public void testHighLight()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index)
				.setTypes(type)
				//.setQuery(QueryBuilders.matchQuery("name", "Fresh")) //????????????
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
	 * ??????
	 */
	@Test
	public void testGroupBy()
	{
		SearchResponse searchResponse = transportClient.prepareSearch(index).setTypes(type)
				.setQuery(QueryBuilders.matchAllQuery())
				.setSearchType(SearchType.QUERY_THEN_FETCH)
				.addAggregation(AggregationBuilders.terms("group_age")
						.field("age").size(0))//??????age?????????????????????10???size(0)??????10
				.get();
		
		Terms terms = searchResponse.getAggregations().get("group_age");
		List<Bucket> buckets = terms.getBuckets();
		for(Bucket bt : buckets)
		{
			System.out.println(bt.getKey() + " " + bt.getDocCount());
		}
	}
	
	/**
	 * ????????????,??????????????????sum???????????????????????????????????????
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
