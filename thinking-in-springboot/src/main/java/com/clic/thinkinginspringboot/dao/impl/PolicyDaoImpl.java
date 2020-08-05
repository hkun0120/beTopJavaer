package com.clic.thinkinginspringboot.dao.impl;

import com.clic.thinkinginspringboot.bean.Policy;
import com.clic.thinkinginspringboot.dao.PolicyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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
    public void addPolicy(Policy policy) {
        String sql = "insert into policy(cntrNo,applNo,applDate) values (?,?,?)";
        jdbcTemplate.update(sql,policy.getCntrNo(),policy.getApplNo(),policy.getApplDate());
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
