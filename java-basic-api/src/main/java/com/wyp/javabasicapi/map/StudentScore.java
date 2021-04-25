package com.wyp.javabasicapi.map;

/**
 * @author wangyupeng
 * @date 2021年04月25日 9:25
 */
public class StudentScore {
    private String stuName;
    private String subject;
    private Integer score;

    public StudentScore(String name, String subject, Integer score) {
        this.stuName = name;
        this.subject = subject;
        this.score = score;
    }

    public StudentScore() {

    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
