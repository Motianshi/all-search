package com.anqi.es.highClient;

import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//DeleteRequest GetRequest UpdateRequest 都是根据 id 操作文档，在实际应用中不常用

@Service
public class RestHighLevelClientService {

    @Autowired
    private RestHighLevelClient client;

    public CreateIndexResponse createIndex(String indexName, String settings, String mapping) throws IOException{
        CreateIndexRequest request = new CreateIndexRequest(indexName);

        if (null != settings && !"".equals(settings)) {
            request.settings(settings, XContentType.JSON);
        }
        if (null != mapping && !"".equals(mapping)) {
            request.mapping(mapping, XContentType.JSON);
        }
        return client.indices().create(request, RequestOptions.DEFAULT);
    }


    public AcknowledgedResponse deleteIndex(String ... indexNames) throws IOException{
        DeleteIndexRequest request = new DeleteIndexRequest(indexNames);

        return client.indices().delete(request, RequestOptions.DEFAULT);
    }


    public boolean indexExists(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    public DeleteResponse deleteDoc(String indexName, String id) throws IOException{
        DeleteRequest request = new DeleteRequest(indexName, id);
        return client.delete(request, RequestOptions.DEFAULT);
    }

    public UpdateResponse updateDoc(String indexName, String id) throws IOException{
        UpdateRequest request = new UpdateRequest(indexName, id);
        return client.update(request, RequestOptions.DEFAULT);
    }

    public IndexResponse addDoc(String indexName, String source) throws IOException{
        IndexRequest request = new IndexRequest(indexName);

        request.source(source, XContentType.JSON);

        return client.index(request, RequestOptions.DEFAULT);

    }


    public SearchResponse search(String field, String key, int page, int size, String ... indexNames) throws IOException{
        SearchRequest request = new SearchRequest(indexNames);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(new MatchQueryBuilder(field, key))
                .from(page)
                .size(size);
        request.source(builder);
        return client.search(request, RequestOptions.DEFAULT);
    }

    public SearchResponse termSearch(String field, String key, int page, int size, String ... indexNames) throws IOException{
        SearchRequest request = new SearchRequest(indexNames);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(new TermQueryBuilder(field, key))
                .from(page)
                .size(size);
        request.source(builder);
        return client.search(request, RequestOptions.DEFAULT);
    }

    public BulkResponse importAll(String indexName, String ... source) throws IOException{
        BulkRequest request = new BulkRequest();
        for (String s : source) {
            request.add(new IndexRequest(indexName).source(s, XContentType.JSON));
        }
        return client.bulk(request, RequestOptions.DEFAULT);
    }



}
