## 使用指南
<p align="center">
  <a href=""><img src="https://img.shields.io/badge/版本-1.0-brightgreen.svg" alt="版本"></a>
  <a href="#公众号"><img src="https://img.shields.io/badge/公众号-Java后端架构充电宝-blue.svg" alt="公众号"></a>
  <a href="#投稿"><img src="https://img.shields.io/badge/support-投稿-critical.svg" alt="投稿"></a>
  <a href="https://www.cnblogs.com/haixiang/p/11078875.html"><img src="https://img.shields.io/badge/blog-Haixiang-important" alt="博客"></a>
</p>


## 目录
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── anqi
│   │   │           └── es
│   │   │               ├── DemoEsApplication.java
│   │   │               ├── Main.java
│   │   │               ├── client
│   │   │               │   └── ESClientConfig.java     老版本RestClient封装，这里不会使用
│   │   │               ├── controller
│   │   │               │   └── EsController.java       搜索测试接口
│   │   │               ├── highclient
│   │   │               │   ├── RestHighLevelClientConfig.java      Client配置
│   │   │               │   └── RestHighLevelClientService.java     搜索API
│   │   │               └── util
│   │   │                   └── SnowflakeIdWorker.java      Twitter的雪花算法用来生成文档id
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       ├── java
│       │   └── com
│       │       └── anqi
│       │           └── es
│       │               ├── DemoEsApplicationTests.java
│       │               ├── ESRestClientTest.java
│       │               ├── ESRestHighClient.java
│       │               └── highclient
│       │                   └── RestHighLevelClientServiceTest.java
│       └── resources
│           └── application.properties
├── pom.xml

```