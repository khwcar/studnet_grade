package com.khw.studnet_grade.entity;

public class Course {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.cno
     *
     * @mbggenerated Mon Dec 28 13:57:40 CST 2020
     */
    private Integer cno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column course.cname
     *
     * @mbggenerated Mon Dec 28 13:57:40 CST 2020
     */
    private String cname;

    @Override
    public String toString() {
        return "Course{" +
                "cno=" + cno +
                ", cname='" + cname + '\'' +
                '}';
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.cno
     *
     * @return the value of course.cno
     * @mbggenerated Mon Dec 28 13:57:40 CST 2020
     */
    public Integer getCno() {
        return cno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.cno
     *
     * @param cno the value for course.cno
     * @mbggenerated Mon Dec 28 13:57:40 CST 2020
     */
    public void setCno(Integer cno) {
        this.cno = cno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column course.cname
     *
     * @return the value of course.cname
     * @mbggenerated Mon Dec 28 13:57:40 CST 2020
     */
    public String getCname() {
        return cname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column course.cname
     *
     * @param cname the value for course.cname
     * @mbggenerated Mon Dec 28 13:57:40 CST 2020
     */
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}