package com.anqi.es;

import com.anqi.es.highclient.RestHighLevelClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ESRestHighClient {


    @Autowired
    RestHighLevelClientService service;

//    @Before
//    public void testRestHighClinet() {
//
//        RestClientBuilder restClientBuilder = RestClient.builder(
//                new HttpHost("localhost", 9200, "http")
//        );
//
//        Header[] defaultHeaders = new Header[]{
//                new BasicHeader("Accept", "*/*"),
//                new BasicHeader("Charset", "UTF-8"),
//                new BasicHeader("E_TOKEN", "esestokentoken")
//        };
//        restClientBuilder.setDefaultHeaders(defaultHeaders);
//
//        restClientBuilder.setFailureListener(new RestClient.FailureListener(){
//            @Override
//            public void onFailure(Node node) {
//                System.out.println("监听失败");
//            }
//        });
//
//        restClientBuilder.setRequestConfigCallback(builder ->
//                builder.setConnectTimeout(5000).setSocketTimeout(15000));
//
//        RestHighLevelClient highClient = new RestHighLevelClient(restClientBuilder);
//
//        restHighLevelClient = highClient;
//        service = new RestHighLevelClientService();
//    }

    @Test
    public void testAddIndex() {
        String settings = "" +
                "  {\n" +
                "      \"number_of_shards\" : \"2\",\n" +
                "      \"number_of_replicas\" : \"0\"\n" +
                "   }";

        String mappings = "" +
                "{\n" +
                "    \"properties\": {\n" +
                "      \"proId\" : {\n" +
                "        \"type\": \"keyword\",\n" +
                "        \"ignore_above\": 64\n" +
                "      },\n" +
                "      \"name\" : {\n" +
                "        \"type\": \"text\",\n" +
                "        \"analyzer\": \"ik_max_word\", \n" +
                "        \"search_analyzer\": \"ik_smart\",\n" +
                "        \"fields\": {\n" +
                "          \"keyword\" : {\"ignore_above\" : 256, \"type\" : \"keyword\"}\n" +
                "        }\n" +
                "      },\n" +
                "      \"mytimestamp\" : {\n" +
                "        \"type\": \"date\",\n" +
                "        \"format\": \"epoch_millis\"\n" +
                "      },\n" +
                "      \"createTime\" : {\n" +
                "        \"type\": \"date\",\n" +
                "        \"format\": \"yyyy-MM-dd HH:mm:ss\"\n" +
                "      }\n" +
                "    }\n" +
                "}";

        try {
            service.createIndex("idx_pro", settings, mappings);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("创建索引失败");
        }

    }

    @Test
    public void deleteIndex() throws IOException {
        service.deleteIndex("idx_pro");
    }

    @Test
    public void addDoc() {
        service.addDoc();
    }

    @Test
    public void testSearch() {
        service.
    }

    @Test
    public void testMain() {
        Date date = new Date();
        System.out.println(date.getTime());
    }

}
