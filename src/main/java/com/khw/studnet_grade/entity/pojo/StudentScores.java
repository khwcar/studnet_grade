package com.khw.studnet_grade.entity.pojo;

import com.khw.studnet_grade.entity.Score;
import com.khw.studnet_grade.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName: StudentScores用于插入
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/29  15:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentScores {
    private Student student;
    private List<Score> scores;

    @Override
    public String toString() {
        return "StudentScores{" +
                "student=" + student +
                ", scores=" + scores +
                '}';
    }
}
