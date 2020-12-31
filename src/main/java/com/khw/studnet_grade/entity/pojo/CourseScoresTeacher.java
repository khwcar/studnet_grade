package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CourseScoresTeacher
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  18:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseScoresTeacher {
    private String cname;
    private String score;
    private String tname;

    @Override
    public String toString() {
        return "CourseScoresTeacher{" +
                "cname='" + cname + '\'' +
                ", score='" + score + '\'' +
                ", tname='" + tname + '\'' +
                '}';
    }
}
