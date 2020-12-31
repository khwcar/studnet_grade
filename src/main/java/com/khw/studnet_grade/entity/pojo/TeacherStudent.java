package com.khw.studnet_grade.entity.pojo;

import com.khw.studnet_grade.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 阿伟
 * @version 1.0
 * @date 2020/12/12 12:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherStudent {
    private Integer tno;
    private String tname;
    private List<Student> students;

    @Override
    public String toString() {
        return "TeacherByStudent{" +
                "tno=" + tno +
                ", tname='" + tname + '\'' +
                ", students=" + students +
                '}';
    }
}
