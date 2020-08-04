package com.clic.thinkinginspringboot.dao;

import com.clic.thinkinginspringboot.bean.Policy;

import java.util.List;

/**
 * @description: policy dao
 * @author: H.K
 * @create: 2020-08-04 17:18
 */
public interface PolicyDao {
    void addPolicy(Policy policy);
    void deletePolicyByCntrNo(String cntrNo);
    void updateByCntrNo(Policy policy);
    List<Policy> queryAll();
}
