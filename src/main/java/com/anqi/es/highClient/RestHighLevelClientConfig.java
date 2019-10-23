package com.anqi.es.highClient;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Node;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestHighLevelClientConfig {

    String hostname;

    int port;

    String scheme;

    @Bean
    public RestClientBuilder restClientBuilder() {
        RestClientBuilder restClientBuilder = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        );

        Header[] defaultHeaders = new Header[]{
                new BasicHeader("Accept", "*/*"),
                new BasicHeader("Charset", "UTF-8"),
                //设置token 是为了安全 网关可以验证token来决定是否发起请求 我们这里只做象征性配置
                new BasicHeader("E_TOKEN", "esestokentoken")
        };
        restClientBuilder.setDefaultHeaders(defaultHeaders);

        restClientBuilder.setFailureListener(new RestClient.FailureListener(){
            @Override
            public void onFailure(Node node) {
                System.out.println("监听某个es节点失败");
            }
        });

        restClientBuilder.setRequestConfigCallback(builder ->
                builder.setConnectTimeout(5000).setSocketTimeout(15000));

        return restClientBuilder;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(RestClientBuilder restClientBuilder) {
        return new RestHighLevelClient(restClientBuilder);
    }
}
