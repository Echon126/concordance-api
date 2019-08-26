package com.concordance.example.repository;

import com.concordance.example.data.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2019-08-23 17:36
 **/
public interface UserRepostory  extends ElasticsearchRepository<User, String> {
}
