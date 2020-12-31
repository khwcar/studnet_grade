package com.khw.studnet_grade.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 阿伟
 * @version 1.0
 * @date 2020/12/28 21:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvgScore {
    private String sname;
    private Double avgs;

    @Override
    public String toString() {
        return "AvgScore{" +
                "sname='" + sname + '\'' +
                ", avg=" + avgs +
                '}';
    }
}
