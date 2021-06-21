package com.msb.learning.thinkinginspringboot.controller;

import com.msb.learning.thinkinginspringboot.annotation.Sensitive;
import com.msb.learning.thinkinginspringboot.bean.Policy;
import com.msb.learning.thinkinginspringboot.service.PolicyService;
import com.msb.learning.thinkinginspringboot.util.AnnoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: H.K
 * @create: 2020-12-01 16:57
 */
@RestController
@RequestMapping("/aop")
public class AopController {
    @Autowired
    PolicyService policyService;
    @Autowired
    AnnoUtil annoUtil;

    @GetMapping("/{name}")
    public String testAop() {
        Policy policy = new Policy();
        policy.setCntrNo("sdsd");
        policyService.testEncrypt(policy);
        annoUtil.testEncrypt(policy);
        return "Hello " ;


    }

    @Sensitive
    public String testEncrypt(Policy request) {
        System.out.println("testEncrypt 业务逻辑入参 request:{}"+ request.toString());
        return null;
    }

}
