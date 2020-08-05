package com.clic.thinkinginspringboot.service;

import com.clic.thinkinginspringboot.bean.Policy;
import com.clic.thinkinginspringboot.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: H.K
 * @create: 2020-08-05 14:14
 */
@Service
public class PolicyService {
    @Autowired
    private PolicyDao policyDao;

    public void addPolicy(Policy policy){
        policyDao.addPolicy(policy);
    }

    public List<Policy> queryAll(){
        return policyDao.queryAll();
    }
}
