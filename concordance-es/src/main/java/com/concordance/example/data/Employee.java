package com.concordance.example.data;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2019-08-21 14:07
 **/

@Data
@Document(indexName = "company", type = "employee", shards = 1, replicas = 0, refreshInterval = "-1")
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String about;
    private long time;
    private String address;
    private String nan;
}
