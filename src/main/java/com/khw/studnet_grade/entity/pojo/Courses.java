package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 阿伟
 * @version 1.0
 * @date 2020/12/12 10:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courses {
    private Integer cno;
    private String cname;
    private Integer tno;
    private String tname;

    @Override
    public String toString() {
        return "Courses{" +
                "cno=" + cno +
                ", cname='" + cname + '\'' +
                ", tno=" + tno +
                ", tname='" + tname + '\'' +
                '}';
    }
}
