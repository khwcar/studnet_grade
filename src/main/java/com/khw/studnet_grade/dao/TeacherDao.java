package com.khw.studnet_grade.dao;


import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.entity.Middle;
import com.khw.studnet_grade.entity.Teacher;
import com.khw.studnet_grade.entity.TeacherExample;
import com.khw.studnet_grade.entity.pojo.CourseTeacher;
import com.khw.studnet_grade.entity.pojo.TeacherCourse;
import com.khw.studnet_grade.entity.pojo.TeacherMiddle;
import com.khw.studnet_grade.mapper.TeacherMapper;
import com.khw.studnet_grade.mapper.extend.StudentScoreMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: TeacherDao
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  15:36
 */
@Slf4j
@Component
public class TeacherDao {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    StudentScoreMapper studentScoreMapper;

    /**
     * 根据老师工号查询老师的信息
     *
     * @param cno
     * @return
     */
    public List<Teacher> selectByPrimaryKey(Integer[] cno) {
        List<Teacher> teachers = new ArrayList<>();
        for (Integer cnos : cno) {
            Teacher teacher = teacherMapper.selectByPrimaryKey(cnos);
            teachers.add(teacher);
        }
        return teachers;
    }

    /**
     * 修改老师的信息
     *
     * @param teacher
     * @return
     */
    public boolean updateByPrimaryKey(Teacher teacher) {
        if (null == teacher) {
            log.error("传入的数据为null");
            return false;
        }
        int i = teacherMapper.updateByPrimaryKey(teacher);
        log.info("修改的条数为：{}", i);
        return i > 0;
    }

    /**
     * 批量删除老师的信息
     *
     * @param cno
     * @return
     */
    public boolean deleteByExample(Integer[] cno) {
        int i = 0;
        if (cno == null && cno.length == 0) {
            log.error("你输入的老师工号不能为null");
            return false;
        }
        //用数组初始化list
        List<Integer> integers = Arrays.asList(cno);
        //根据条件删除（delete from student where sno=snos）读音yikezanpo
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andTnoIn(integers);
        i = teacherMapper.deleteByExample(example);
        log.info("成功删除" + i);
        return i > 0;
    }

    /**
     * 插入老师数据
     *
     * @param teacher
     * @return
     */
    public boolean insertSelective(Teacher teacher) {
        if (null == teacher) {
            log.error("老师不能为null");
            return false;
        } else {
            int i = teacherMapper.insertSelective(teacher);
            log.info("成功插入老师：" + i);
            return i > 0;
        }
    }


    /**
     * 根据姓名查询该老师
     *
     * @param cname
     * @return
     */
    public List<Teacher> selectByExample(String cname) {
        TeacherExample example = new TeacherExample();
        TeacherExample.Criteria criteria = example.createCriteria();
        criteria.andTnameEqualTo(cname);
        List<Teacher> teachers = teacherMapper.selectByExample(example);
        return teachers;
    }

    /**
     * 查询老师对应的课程
     *
     * @return
     */
    public List<TeacherCourse> teachercourse() {
        List<TeacherCourse> teachercourse = studentScoreMapper.teachercourse();
        return teachercourse;
    }

    /**
     * 对老师和课程进行插入
     *
     * @param teacherMiddle
     * @return
     * @throws Demoxception
     */
    @Transactional(rollbackFor = Demoxception.class)
    public boolean insertTeacherMiddle(TeacherMiddle teacherMiddle) throws Demoxception {
        if (teacherMiddle == null) {
            log.error("传入的参数为空请检查");
            return false;
        }
        Teacher teachers = teacherMiddle.getTeachers();
        Integer integer = studentScoreMapper.insert(teachers);
        if (integer > 0) {
            log.info("老师表的数据插入成功");
            Middle middles = teacherMiddle.getMiddles();
            middles.setTno(teachers.getTno());
            Integer integer1 = studentScoreMapper.insertTeacherMiddles(middles);
            if (integer1 > 0) {
                log.info("老师表课程表全部插入成功");
                return true;
            } else {
                log.error("课程表信息插入失败，事件将会回滚到最初状态");
                throw new Demoxception("插入失败");
            }
        } else {
            return false;
        }
    }

    /**
     * 修改老师的信息
     *
     * @param teacherMiddle
     * @return
     */
    @Transactional(rollbackFor = Demoxception.class)
    public boolean updateByteacherBymiddle(TeacherMiddle teacherMiddle) throws Demoxception {
        Teacher teachers = teacherMiddle.getTeachers();
        Middle middles = teacherMiddle.getMiddles();
        middles.setTno(teachers.getTno());
        System.out.println(teachers.getTno());
        Integer integer = studentScoreMapper.updateByteacher(teachers);
        if (integer > 0) {
            Integer integer1 = studentScoreMapper.updateBymiddle(middles);
            if (integer1 > 0) {
                System.out.println("修改成功");
                return true;
            } else {
                log.error("老师对应的课程表插入失败");
                throw new Demoxception("老师对应课程表插入失败");
            }
        } else {
            log.error("老师表插入失败");
            return false;
        }
    }

    /**
     * 根据性别查询学生信息
     *
     * @param sex
     * @return
     */
    public List<CourseTeacher> selectBysex(Integer sex) {
        return studentScoreMapper.selectBysex(sex);
    }

    /**
     * 根据老师姓名查询老师的信息和对应的课程
     *
     * @param tname
     * @return
     */
    public List<CourseTeacher> selectLikeBytname(String tname) {
        return studentScoreMapper.selectLikeBytname(tname);
    }
}
