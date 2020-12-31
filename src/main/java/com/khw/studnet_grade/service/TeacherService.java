package com.khw.studnet_grade.service;

import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.entity.Teacher;
import com.khw.studnet_grade.entity.pojo.CourseTeacher;
import com.khw.studnet_grade.entity.pojo.TeacherCourse;
import com.khw.studnet_grade.entity.pojo.TeacherMiddle;

import java.util.List;

/**
 * @ClassName: TeacherService
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  15:55
 */
public interface TeacherService {
    List<Teacher> selectByPrimaryKey(Integer[] cno);

    boolean updateByPrimaryKey(Teacher teacher);

    boolean deleteByExample(Integer[] cno);

    boolean insertSelective(Teacher teacher);

    List<Teacher> selectByExample(String cname);

    List<TeacherCourse> teachercourse();

    boolean insertTeacherMiddle(TeacherMiddle teacherMiddle) throws Demoxception;

    boolean updateByteacherBymiddle(TeacherMiddle teacherMiddle) throws Demoxception;

    List<CourseTeacher> selectBysex(Integer sex);

    List<CourseTeacher> selectLikeBytname(String tname);
}
