package com.khw.studnet_grade.service;

import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.entity.Score;
import com.khw.studnet_grade.entity.Student;
import com.khw.studnet_grade.entity.pojo.*;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Student> selectByPrimaryKey(Integer[] sno);

    boolean updateByPrimaryKey(Student student);

    boolean deleteByExample(Integer[] sno) throws Demoxception;

    boolean insert(Student student);

    List<Student> selectByExample(String sname);

    List<StudentScore> selectBysnoScores(Integer[] sno);

    List<StudentMark> SelectByAllStudentCourse(List sno);


    List<StudentMark> selectBysnoBycno(Map map);

    List<Student> selectBysno(List list);

    List<Student> selectBysnos(List list);

    List<StudentAll> selectAll();

    List<AvgScore> avgsCore();

    AvgScore avgsBysname(String sname);

    boolean insertBycnoBytno(Score score) throws Demoxception;

    boolean insertBystudentBycore(StudentScores studentScores) throws Demoxception;

    CourseScore selectBycnoByscore(Integer cno, double min, double max);

    List<StudentAll> selectAllpage();

    boolean updateBystudentByscore(StudentScores studentScores) throws Demoxception;

    List<Student> selectLikeBysname(String sname);

    List<Student> selectLikeByphone(String phone);

    List<StudentMark> selectBysnoBycnoOne(Integer sno, Integer cno);
}
