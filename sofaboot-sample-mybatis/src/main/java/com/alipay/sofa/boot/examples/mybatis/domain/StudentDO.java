package com.alipay.sofa.boot.examples.mybatis.domain;

/**
 * StudentDO
 *
 * @author Deven
 * @version : StudentDO, v 0.1 2020-03-09 23:17 Deven Exp$
 */
public class StudentDO {

    private Integer id;

    private String name;

    private Integer age;

    private String address;

    private Integer sex;

    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

}
