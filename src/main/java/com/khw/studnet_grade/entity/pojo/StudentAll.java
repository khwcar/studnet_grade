package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: StudentAll
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  18:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAll {
    private Integer sno;
    private String sname;
    private List<CourseScoresTeacher> courses;

    @Override
    public String toString() {
        return "StudentAll{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", courses=" + courses +
                '}';
    }
}
