package com.msb.learning.thinkinginspringboot.basic;

/**
 * @description:
 * @author: H.K
 * @create: 2021-04-09 11:39
 */
public class People {
    private String name;
    private String sex;
    private String occ;
    private String height;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOcc() {
        return occ;
    }

    public void setOcc(String occ) {
        this.occ = occ;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", occ='" + occ + '\'' +
                ", height='" + height + '\'' +
                '}';
    }

}
