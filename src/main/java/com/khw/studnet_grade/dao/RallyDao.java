package com.khw.studnet_grade.dao;

import com.khw.studnet_grade.entity.Student;
import com.khw.studnet_grade.entity.pojo.StudentMark;
import com.khw.studnet_grade.mapper.extend.TesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: TestDao
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/15  10:05
 */
@Component
public class RallyDao {
    @Autowired
    TesMapper mapper;

    public List<StudentMark> SelectByAllStudentCourse(List sno) {
        Map maps = new HashMap();
        maps.put("customerIds", sno);
        List<StudentMark> studentMarks = mapper.SelectByAllStudentCourse(maps);
        return studentMarks;
    }

    public List<StudentMark> selectBysnoBycno(Map map) {
        List<StudentMark> studentMarks = mapper.selectBysnoBycno(map);
        return studentMarks;
    }

    /**
     * 测试mapper中传list
     *
     * @param list
     * @return
     */
    public List<Student> selectBysno(List list) {
        List<Student> students = mapper.selectBysno(list);
        return students;
    }

    /**
     * 测试mapper中传递自定义名字参数
     *
     * @param list
     * @return
     */
    public List<Student> selectBysnos(List list) {
        List<Student> students = mapper.selectBysnos(list);
        return students;
    }
}
