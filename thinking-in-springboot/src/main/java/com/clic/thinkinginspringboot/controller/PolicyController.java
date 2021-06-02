package com.clic.thinkinginspringboot.controller;

import com.clic.thinkinginspringboot.bean.Policy;
import com.clic.thinkinginspringboot.service.ExecutorService;
import com.clic.thinkinginspringboot.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: H.K
 * @create: 2020-08-05 14:18
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {
    @Autowired
    PolicyService policyService;

    @Autowired
    ExecutorService executorService;

    @PostMapping("add")
    public void add(Policy policy){
//        Policy p = new Policy();
//        p.setApplDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        p.setApplNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("hhmmss")));
//        p.setCntrNo(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss")));
        policyService.addPolicy(policy);
    }

    @GetMapping("queryAll")
    public List<Policy> queryAll(){
        return policyService.queryAll();
    }

    @GetMapping("test")
    public void test() throws ExecutionException, InterruptedException {
        executorService.test();
    }

    @GetMapping("testOrder")
    public void testOrder() throws ExecutionException, InterruptedException {
        executorService.orderThread();
    }

}
