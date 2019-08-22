package com.concordance.example.repository;

import com.concordance.example.data.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 西安中科天塔科技股份有限公司
 * Copyright (c) 2018-2028, tianta All Rights Reserved.<br/>
 * <b>@description:
 *
 * <b>@author: zwj
 *
 * <b>@create: 2019-08-21 14:14
 **/

public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {
/**
     * 查询雇员信息
     *
     * @param id
     * @return
     */

    Employee queryEmployeeById(String id);
}
