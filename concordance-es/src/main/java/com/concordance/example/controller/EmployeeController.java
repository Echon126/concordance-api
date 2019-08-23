package com.concordance.example.controller;

import com.concordance.example.data.Employee;
import com.concordance.example.repository.EmployeeRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        Employee employee;
        for (int i = 0; i < 1000; i++) {
            employee = new Employee();
            employee.setId("1"+i+i);
            employee.setFirstName("张三"+i);
            employee.setAge(10+i);
            employee.setTime(new Date());
            employee.setAddress("西安"+i);
            employee.setAbout("ass");
            employee.setNan("sdfsdfsdfsdfsd");
            this.repository.save(employee);
            System.out.println("=========end==========");

        }
        return "success";
    }


    @RequestMapping("/query")
    public Employee query(String id) {
        Employee accountInfo = repository.queryEmployeeById(id);
        System.out.println(new Gson().toJson(accountInfo));

        System.out.println(repository.findAll());
        return accountInfo;
    }

}


















