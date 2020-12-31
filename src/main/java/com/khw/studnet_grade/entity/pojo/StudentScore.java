package com.khw.studnet_grade.entity.pojo;

import com.khw.studnet_grade.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 学生对应的多门课程的成绩
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentScore {
    private Integer sno;
    private String sname;
    private List<Course> courses;

    @Override
    public String toString() {
        return "StudentScore{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", courses=" + courses +
                '}';
    }
}
