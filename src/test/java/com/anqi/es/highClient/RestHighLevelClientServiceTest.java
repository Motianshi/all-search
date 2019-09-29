package com.anqi.es.highClient;

import com.anqi.es.DemoEsApplication;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

//有RunWith才会有ioc容器
@SpringBootTest(classes = DemoEsApplication.class)
@RunWith(SpringRunner.class)
@ComponentScan("com.anqi.es")
public class RestHighLevelClientServiceTest {

    @Autowired
    RestHighLevelClientService service;

    @Test
    public void createIndex() throws IOException{
        String settings =
                "{\n" +
                        " \"number_of_shards\" : 1,\n" +
                        " \"number_of_replicas\" : 0\n" +
                        " }\n" ;

        //设置 id 为 keyword 不分词，用来精准匹配，存放主键
        String mappings =
                "{\n" +
                        "  \"properties\": {\n" +
                        "    \"id\": {\n" +
                        "      \"type\": \"keyword\"\n" +
                        "    },\n" +
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

        service.createIndex("idx_cloth", null, null);
    }

    @Test
    public void deleteIndex() throws IOException{
        service.deleteIndex("idx_clouthing");
    }

    @Test
    public void indexExists() throws IOException{
        System.out.println(service.indexExists("idx_tt"));
    }


    @Test
    public void addDoc() throws IOException {
        String source = "{\n" +
                "  \"id\" : \"item id\",\n" +
                "  \"name\" : \"adidas cloth9\",\n" +
                "  \"price\" : 299,\n" +
                "  \"num\" : 100,\n" +
                "  \"date\" : \"2019-09-10\"\n" +
                "}";
        IndexResponse response = service.addDoc("idx_cloth", source);
        System.out.println(response.toString());
    }

    @Test
    public void search() throws IOException{
        SearchResponse response = service.search("name", "adidas",0, 5, "idx_clouthing");
        Arrays.asList(response.getHits().getHits())
                .forEach(e -> System.out.println(e.getSourceAsString()));
    }

    @Test
    public void termSearch() throws IOException{
       // SearchResponse response = service.termSearch("name", "")
    }

    @Test
    public void deleteDoc() throws IOException{
        String id = "";
        SearchResponse search = service.search("id", "2", 0, 5, "idx_clouthing");

        for (SearchHit hit : search.getHits().getHits()) {
            id = hit.getId();
        }



        service.deleteDoc("idx_clouthing", id);
    }
}