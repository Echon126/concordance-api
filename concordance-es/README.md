### @Document注解
String indexName();//索引库的名称，建议以项目的名称命名
String type() default "";//类型，建议以实体的名称命名
short shards() default 5;//默认分区数
short replicas() default 1;//每个分区默认的备份数
String refreshInterval() default "1s";//刷新间隔
String indexStoreType() default "fs";//索引文件存储类型



https://www.cnblogs.com/cjsblog/p/9459781.html


### springboot集成ElasticSearch 注意事项
