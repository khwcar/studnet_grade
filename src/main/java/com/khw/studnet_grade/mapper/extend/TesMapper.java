package com.khw.studnet_grade.mapper.extend;

import com.khw.studnet_grade.entity.Student;
import com.khw.studnet_grade.entity.pojo.StudentMark;
import com.khw.studnet_grade.mapper.StudentMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: TesMappert
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/14  18:26
 */
@Mapper
@Repository
public interface TesMapper extends StudentMapper {
    /**
     * 根据学号批量查询该学生的课程成绩
     *
     * @param map
     * @return
     */
    List<StudentMark> SelectByAllStudentCourse(Map map);

    /**
     * 根据学号和课程号查询该学生对性的课程成绩
     *
     * @param map
     * @return
     */
    List<StudentMark> selectBysnoBycno(Map map);

    /**
     * 测试传递参数为list的操作
     *
     * @param sno
     * @return
     */
    List<Student> selectBysno(List sno);

    /**
     * 测试传递参数自定义名字
     *
     * @param sno
     * @return
     */
    List<Student> selectBysnos(@Param("sno") List sno);
}
