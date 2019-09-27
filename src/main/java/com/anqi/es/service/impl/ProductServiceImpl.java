//package com.anqi.es.service.impl;
//
//import com.anqi.es.item.Product;
//import com.anqi.es.repository.ProductRepository;
//import com.anqi.es.service.ProductService;
//import org.elasticsearch.index.query.MatchQueryBuilder;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.data.elasticsearch.core.query.SearchQuery;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@EnableElasticsearchRepositories("com.anqi.es.repository")
//public class ProductServiceImpl implements ProductService {
//
//    @Autowired
//    ProductRepository repository;
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public Product save(Product product) {
//        return repository.save(product);
//    }
//
//    @Override
//    public boolean delete(Product product) {
//        return false;
//    }
//
//    @Override
//    public List<Product> getByName(String name) {
//
//        List<Product> result = new ArrayList<>();
//
//        MatchQueryBuilder query = new MatchQueryBuilder("name",name);
//        Iterable<Product> search = repository.search(query);
//
//        search.forEach(e -> result.add(e));
//
//        return result;
//    }
//
//    @Override
//    public Page<Product> pageQuery(int page, int size, String key) {
//
//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(QueryBuilders.matchPhrasePrefixQuery("name", key))
//                .withPageable(PageRequest.of(page, size))
//                .build();
//        return repository.search(searchQuery);
//    }
//}
