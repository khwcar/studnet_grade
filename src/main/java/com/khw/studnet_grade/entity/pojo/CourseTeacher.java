package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: CourseTeacher
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/30  15:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseTeacher {
    private String cname;
    private String tname;
    private String age;
    private Integer sex;

    @Override
    public String toString() {
        return "CourseTeacher{" +
                "cname='" + cname + '\'' +
                ", tname='" + tname + '\'' +
                ", age='" + age + '\'' +
                ", sex=" + sex +
                '}';
    }
}
