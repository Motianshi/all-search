package com.anqi.es.service;

import com.anqi.es.item.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    long count();

    Product save(Product product);

    boolean delete(Product product);

    List<Product> getByName(String name);

    Page<Product> pageQuery(int page, int size, String key);
}
