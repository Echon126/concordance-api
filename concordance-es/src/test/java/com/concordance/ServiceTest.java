package com.concordance;

import com.concordance.example.EsApplication;
import com.concordance.example.data.Employee;
import com.concordance.example.repository.EmployeeRepository;
import com.google.gson.Gson;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2019-08-27 15:22
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {EsApplication.class})
public class ServiceTest {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void testPage() {
        System.out.println("=================");
        Page<Employee> page = repository.findAll(PageRequest.of(1, 20));
        System.out.println(page.getContent());
        System.out.println(page.getTotalPages());
        System.out.println(page.getSort());
        System.out.println("=================");

        long count = repository.countByLastName("1");
        System.out.println(count);
    }


    @Test
    public void TestSearch() {
        QueryBuilder queryBuilders = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("firstName", "张三0"));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilders)
                .build();

        Page<Employee> page = repository.search(searchQuery);
        System.out.println(page.getTotalPages());
        System.out.println(page.get());
        System.out.println(page.getContent());
    }


    @Test
    public void TestTemple() {
        QueryBuilder queryBuilders = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("firstName", "张三0"));

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilders)
                .build();

        List<Employee> list = elasticsearchTemplate.queryForList(searchQuery, Employee.class);
        System.out.println(list);
    }


    @Test
    public void TestBulk() {
        Employee employee;
        List<Employee> list = new ArrayList<>();
        int count = 100;
        for (int i = 0; i < 10000; i++) {
            count++;
            employee = new Employee();
            employee.setId("1000" + count);
            employee.setNan("nan" + count);
            employee.setAddress("address" + count);
            employee.setFirstName("FirstName" + count);
            employee.setTime(new Date().getTime());
            employee.setAbout("about" + count);
            employee.setAge(count);
            employee.setLastName("lastName" + count);
            list.add(employee);

        }

        long start = System.currentTimeMillis();
        int counter = 0;
        System.out.println("======is not======="+this.elasticsearchTemplate.indexExists("company"));
        if (!this.elasticsearchTemplate.indexExists("company")) {
            System.out.println("======index not exists=======");
            elasticsearchTemplate.createIndex("company");
        }
        List<IndexQuery> quays = new ArrayList<>();
        for (Employee e : list) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(e.getId() + "");
            indexQuery.setSource(new Gson().toJson(e));
            indexQuery.setIndexName("company");
            indexQuery.setType("employee");

            quays.add(indexQuery);
            if (counter % 500 == 0) {
                elasticsearchTemplate.bulkIndex(quays);
                quays.clear();
                System.out.println("bulkIndex counter: " + counter);
            }
            counter++;
        }
        if (quays.size() > 0) {
            elasticsearchTemplate.bulkIndex(quays);
        }
        System.out.println("========need time==========" + (System.currentTimeMillis() - start));
        System.out.println("========end=======");
    }
}
