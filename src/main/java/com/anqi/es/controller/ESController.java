package com.anqi.es.controller;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ESController {

    @Autowired
    RestClient restClient;

    @GetMapping("/es")
    public String testEs() {
        String result = "";
        String es_query = "{" +
                "  \"query\": {" +
                "    \"bool\": {" +
                "      \"must\": [" +
                "        {\"match\":{" +
                "          \"title\" : \"apple\"" +
                "        } }," +
                "        {\"range\": {" +
                "          \"price\": {" +
                "            \"gte\": 0.4," +
                "            \"lte\": 1000" +
                "          }" +
                "        }}" +
                "      ]" +
                "    }" +
                "  }" +
                "}";

        Request request = new Request("GET", "/items/_search");
        try {
            HttpEntity entity = new NStringEntity(es_query, ContentType.APPLICATION_JSON);
            request.setEntity(entity);

            Response response = restClient.performRequest(request);
            HttpEntity res = response.getEntity();
            result = EntityUtils.toString(res);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
