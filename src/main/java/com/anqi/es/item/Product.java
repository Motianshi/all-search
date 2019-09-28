//package com.anqi.es.item;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//
//@Document(indexName = "index_product", shards = 3, replicas = 0)
//public class Product implements Serializable {
//
//    //文档全局唯一id
//    @Id
//    private Long id;
//
//    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
//    private String name;
//
//    //不会分词建立索引
//    @Field(type = FieldType.Keyword)
//    private String category;
//
//    private Integer num;
//
//    private double price;
//
//    //index 默认为 true 需要被搜索到的字段需要 index
//    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
//    private String desc;
//
//    public Product(Long id, String name, String category, Integer num, double price, String desc) {
//        this.id = id;
//        this.name = name;
//        this.category = category;
//        this.num = num;
//        this.price = price;
//        this.desc = desc;
//    }
//
//    public Product() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCategory() {
//        return category;
//    }
//
//    public void setCategory(String category) {
//        this.category = category;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public Integer getNum() {
//        return num;
//    }
//
//    public void setNum(Integer num) {
//        this.num = num;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", category='" + category + '\'' +
//                ", num=" + num +
//                ", price=" + price +
//                ", desc='" + desc + '\'' +
//                '}';
//    }
//}
