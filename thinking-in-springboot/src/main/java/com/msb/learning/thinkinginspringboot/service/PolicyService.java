package com.msb.learning.thinkinginspringboot.service;

import com.msb.learning.thinkinginspringboot.annotation.Sensitive;
import com.msb.learning.thinkinginspringboot.bean.Policy;
import com.msb.learning.thinkinginspringboot.dao.PolicyDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PolicyDao policyDao;

    public void addPolicy(Policy policy){
        policyDao.addPolicy(policy);
    }

    public List<Policy> queryAll(){
        return policyDao.queryAll();
    }

    @Sensitive
    public String testEncrypt(Policy request) {
        logger.info("testEncrypt业务逻辑入参 request");
        System.out.println("testEncrypt 业务逻辑入参 request:{}"+ request.toString());
        return null;
    }
}
