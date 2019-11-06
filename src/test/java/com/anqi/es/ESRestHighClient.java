package com.anqi.es;

import com.anqi.es.highclient.RestHighLevelClientService;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Iterator;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ESRestHighClient {

    RestHighLevelClient restHighLevelClient;

    @Autowired
    RestHighLevelClientService service;

    @Before
    public void testRestHighClinet() {

        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );

        Header[] defaultHeaders = new Header[]{
                new BasicHeader("Accept", "*/*"),
                new BasicHeader("Charset", "UTF-8"),
                new BasicHeader("E_TOKEN", "esestokentoken")
        };
        restClientBuilder.setDefaultHeaders(defaultHeaders);

        restClientBuilder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                System.out.println("监听失败");
            }
        });

        restClientBuilder.setRequestConfigCallback(builder ->
                builder.setConnectTimeout(5000).setSocketTimeout(15000));

        RestHighLevelClient highClient = new RestHighLevelClient(restClientBuilder);

        restHighLevelClient = highClient;





    }

    @Test
    public void testIndex() {



        /**
         * 设置分片和复制
         */
//        createIndexRequest.settings(Settings.builder()
//                .put("index.number_of_shards", 2)
//                .put("index.number_of_replicas", 0)
//                .build());
        String settings =
                "{\n" +
                " \"number_of_shards\" : 1,\n" +
                " \"number_of_replicas\" : 0\n" +
                " }\n" ;

        String mappings =
                "{\n" +
                "  \"properties\": {\n" +
                "    \"name\": {\n" +
                "      \"type\": \"text\"\n" +
                "    },\n" +
                "    \"price\": {\n" +
                "      \"type\": \"double\"\n" +
                "    },\n" +
                "    \"num\": {\n" +
                "      \"type\": \"integer\"\n" +
                "    },\n" +
                "    \"date\": {\n" +
                "      \"type\": \"date\",\n" +
                "      \"format\": \"yyyy-MM-dd\"\n" +
                "    }\n" +
                "  }\n" +
                "}";


        try {
            service.createIndex("idx_clouthing", settings, mappings);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建索引失败");
        }


//        DeleteRequest deleteRequest = new DeleteRequest("idx_fruit")
//                .id("1");
//
//        UpdateRequest updateRequest = new UpdateRequest().id("1").index("idx_fruit");
//
//        GetRequest getRequest = new GetRequest("idx_fruit").id("1");

    }


    @Test
    public void testSearch() {
        //没参数就是全部索引中查询
        SearchRequest searchRequest = new SearchRequest("idx_fruit");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termsQuery("user", "anqi"));
        builder.from(0);
        builder.size(5);

        searchRequest.source(builder);

        SearchResponse response=null;

        try {
            response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != response) {

            SearchHits hits = response.getHits();
            Iterator<SearchHit> iterator = hits.iterator();
            while (iterator.hasNext()){
                SearchHit hit = iterator.next();
                String sourceAsString = hit.getSourceAsString();
                System.out.println(sourceAsString);
            }
        }
        System.out.println(response.getHits());
    }

}
