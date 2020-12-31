package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: 一个课程对应多个学生
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/29  16:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseScore {
    private Integer cno;
    private String cname;
    private List<StudentSocreyi> studentSocreyis;

    @Override
    public String toString() {
        return "CourseScore{" +
                "cno=" + cno +
                ", cname='" + cname + '\'' +
                ", studentSocreyis=" + studentSocreyis +
                '}';
    }
}
