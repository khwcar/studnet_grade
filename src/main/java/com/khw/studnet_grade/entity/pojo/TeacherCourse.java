package com.khw.studnet_grade.entity.pojo;

import com.khw.studnet_grade.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 阿伟
 * @version 1.0
 * @date 2020/12/28 22:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCourse {
    private Integer cno;
    private String cname;
    private List<Teacher> teachers;

    @Override
    public String toString() {
        return "TeacherCourse{" +
                "cno=" + cno +
                ", cname='" + cname + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
