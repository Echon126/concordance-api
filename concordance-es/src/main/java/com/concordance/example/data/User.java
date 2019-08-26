package com.concordance.example.data;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2019-08-23 17:34
 **/
@Data
@Document(indexName = "companys", type = "user")
public class User {
    private String id;
    private String userName;
    private String xxxMessage;
}
