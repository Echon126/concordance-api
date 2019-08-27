package com.concordance.example.controller;

import com.concordance.example.data.Employee;
import com.concordance.example.data.User;
import com.concordance.example.repository.EmployeeRepository;
import com.concordance.example.repository.UserRepostory;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    private UserRepostory userRepostory;


    @RequestMapping("/save")
    public String add() {
        User user;
        for (int i = 0; i < 1000; i++) {
            user = new User();
            user.setId("1" + i);
            user.setUserName("李四" + i);
            user.setXxxMessage("message info ....");
            this.userRepostory.save(user);
            System.out.println("=========end==========");

        }
        return "success";
    }


    @RequestMapping("/query")
    public Employee query(String id) {
        Employee accountInfo = repository.queryEmployeeById(id);
        System.out.println(new Gson().toJson(accountInfo));
        Page<Employee> employeePage = repository.findAll(PageRequest.of(1, 20));
        return accountInfo;
    }

}


















