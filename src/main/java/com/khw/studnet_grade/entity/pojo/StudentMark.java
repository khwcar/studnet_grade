package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: StudentMark
 * @Description: 批量学生成绩及格的学生信息
 * @author: kanghongwei
 * @date: 2020/12/15  9:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentMark {
    private Integer sno;
    private String sname;
    private String cname;
    private String score;

    @Override
    public String toString() {
        return "StudentMark{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                ", cname='" + cname + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
