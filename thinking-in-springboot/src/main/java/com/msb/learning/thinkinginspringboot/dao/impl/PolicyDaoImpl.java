package com.msb.learning.thinkinginspringboot.dao.impl;

import com.msb.learning.thinkinginspringboot.bean.Policy;
import com.msb.learning.thinkinginspringboot.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: H.K
 * @create: 2020-08-04 17:23
 */
@Repository
public class PolicyDaoImpl implements PolicyDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Policy addPolicy(Policy policy) {
        System.out.println("insert data to database");
        Policy policy1 = new Policy();
        policy1.setApplNo("11111");
        policy1.setCntrNo(policy.getCntrNo());
        return policy1;
//        String sql = "insert into policy(cntrNo,applNo,applDate) values (?,?,?)";
//        jdbcTemplate.update(sql,policy.getCntrNo(),policy.getApplNo(),policy.getApplDate());
    }

    @Override
    public void deletePolicyByCntrNo(String cntrNo) {

    }

    @Override
    public void updateByCntrNo(Policy policy) {

    }

    @Override
    public List<Policy> queryAll() {
        String sql ="select * from policy";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Policy.class));
    }
}
