package com.khw.studnet_grade.mapper.extend;

import com.khw.studnet_grade.entity.Middle;
import com.khw.studnet_grade.entity.Score;
import com.khw.studnet_grade.entity.Student;
import com.khw.studnet_grade.entity.Teacher;
import com.khw.studnet_grade.entity.pojo.*;
import com.khw.studnet_grade.mapper.StudentMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface StudentScoreMapper extends StudentMapper {
    StudentScore selectBysnoScores(Integer sno);

    TeacherStudent SelectTeacherBysname(String tname);

    List<StudentAll> selectAll();

    List<AvgScore> avgsCore();

    AvgScore avgsBysname(String sname);

    List<TeacherCourse> teachercourse();

    Integer insert(Teacher teacher);

    Integer insertTeacherMiddles(Middle Middle);

    @Override
    int insert(Student record);

    Integer insertBycnoBytno(Score score);

    Integer insertBystudent(Student student);

    Integer inserByScore(Score score);

    CourseScore selectBycnoByscore(Map map);

    List<StudentAll> selectAllpage();

    Integer updateByteacher(Teacher teacher);

    Integer updateBymiddle(Middle middle);

    Integer updateBystudent(Student student);

    Integer updateByscore(Score score);

    List<Student> selectLikeBysname(String sname);

    List<Student> selectLikeByphone(String phone);

    List<CourseTeacher> selectBysex(Integer sex);

    List<CourseTeacher> selectLikeBytname(String tname);

    List<StudentMark> selectBysnoBycnoOne(Map map);
}
