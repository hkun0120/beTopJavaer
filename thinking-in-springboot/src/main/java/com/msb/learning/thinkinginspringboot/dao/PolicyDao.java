package com.msb.learning.thinkinginspringboot.dao;

import com.msb.learning.thinkinginspringboot.bean.Policy;

import java.util.List;

/**
 * @description: policy dao
 * @author: H.K
 * @create: 2020-08-04 17:18
 */
public interface PolicyDao {
    Policy addPolicy(Policy policy);
    void deletePolicyByCntrNo(String cntrNo);
    void updateByCntrNo(Policy policy);
    List<Policy> queryAll();
}
