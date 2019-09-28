package com.anqi.es.highClient;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RestHighLevelClientService {

    @Autowired
    private RestHighLevelClient client;


    public CreateIndexResponse createIndex(String indexName, String settings, String mappings) throws IOException{
        CreateIndexRequest request = new CreateIndexRequest(indexName);

        request.settings(settings, XContentType.JSON)
                .mapping(mappings, XContentType.JSON);

        return client.indices().create(request, RequestOptions.DEFAULT);
    }

    public void deleteIndex(String ... indexNames) throws IOException{
        DeleteIndexRequest request = new DeleteIndexRequest(indexNames);

        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    public boolean indexExict(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

}
