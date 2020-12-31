package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: StudentSocreyi
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/29  16:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentSocreyi {
    private String sname;
    private String score;

    @Override
    public String toString() {
        return "StudentSocreyi{" +
                "sname='" + sname + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
