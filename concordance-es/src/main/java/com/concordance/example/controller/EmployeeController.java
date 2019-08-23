package com.concordance.example.controller;

import com.concordance.example.data.Employee;
import com.concordance.example.repository.EmployeeRepository;
import com.google.gson.Gson;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2019-08-21 14:17
 **/

@RestController
@RequestMapping("/es")
public class EmployeeController {
    @Autowired
    private EmployeeRepository repository;


    @RequestMapping("/save")
    public String add() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setAge(10);
        employee.setAbout("ass");
        this.repository.save(employee);
        return "success";
    }


    @RequestMapping("/query")
    public Employee query() {
        Employee accountInfo = repository.queryEmployeeById("1");
        System.out.println(new Gson().toJson(accountInfo));
        return accountInfo;
    }

}


















