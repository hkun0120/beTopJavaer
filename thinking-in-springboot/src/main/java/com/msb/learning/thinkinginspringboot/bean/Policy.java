package com.msb.learning.thinkinginspringboot.bean;

import com.msb.learning.thinkinginspringboot.annotation.Hidden;

/**
 * @description:
 * @author: H.K
 * @create: 2020-08-04 17:24
 */
public class Policy {

    @Hidden
    private String cntrNo;
    @Hidden
    private String applNo;
    @Hidden
    private String applDate;

    public String getCntrNo() {
        return cntrNo;
    }

    public void setCntrNo(String cntrNo) {
        this.cntrNo = cntrNo;
    }

    public String getApplNo() {
        return applNo;
    }

    public void setApplNo(String applNo) {
        this.applNo = applNo;
    }

    public String getApplDate() {
        return applDate;
    }

    public void setApplDate(String applDate) {
        this.applDate = applDate;
    }
}
