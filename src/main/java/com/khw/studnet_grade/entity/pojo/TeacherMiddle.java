package com.khw.studnet_grade.entity.pojo;

import com.khw.studnet_grade.entity.Middle;
import com.khw.studnet_grade.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: TeacherMiddle
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/29  10:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherMiddle {
    private Teacher teachers;
    private Middle middles;

    @Override
    public String toString() {
        return "TeacherMiddle{" +
                "teachers=" + teachers +
                ", middles=" + middles +
                '}';
    }
}
