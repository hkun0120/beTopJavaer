package com.msb.learning.thinkinginspringboot;

import com.msb.learning.thinkinginspringboot.bean.Policy;
import com.msb.learning.thinkinginspringboot.controller.AopController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ThinkingInSpringbootApplicationTests {
    @Autowired
    AopController aopController;
    @Test
    void contextLoads() {
        Policy policy = new Policy();
        policy.setCntrNo("ddddd");
        policy.setApplNo("aaaaaa");
        aopController.testEncrypt(policy);
    }

}
