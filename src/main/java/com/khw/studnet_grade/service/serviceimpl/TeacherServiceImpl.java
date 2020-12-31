package com.khw.studnet_grade.service.serviceimpl;

import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.dao.MiddleDao;
import com.khw.studnet_grade.dao.TeacherDao;
import com.khw.studnet_grade.entity.Teacher;
import com.khw.studnet_grade.entity.pojo.CourseTeacher;
import com.khw.studnet_grade.entity.pojo.TeacherCourse;
import com.khw.studnet_grade.entity.pojo.TeacherMiddle;
import com.khw.studnet_grade.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: TeacherServiceImpl
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  15:54
 */
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherDao teacherDao;
    @Autowired
    MiddleDao middleDao;

    @Override
    public List<Teacher> selectByPrimaryKey(Integer[] cno) {
        return teacherDao.selectByPrimaryKey(cno);
    }

    /**
     * 修改老师的信息
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean updateByPrimaryKey(Teacher teacher) {
        return teacherDao.updateByPrimaryKey(teacher);
    }

    /**
     * 删除老师的信息（删除信息中所有与该老师有关的信息）
     *
     * @param cno
     * @return
     */
    @Override
    public boolean deleteByExample(Integer[] cno) {
        boolean b = teacherDao.deleteByExample(cno);
        if (b) {
            log.info("老师表数据删除成功");
            boolean b1 = middleDao.deleteByExample(cno);
            if (b1) {
                log.info("老师对应的课程信息删除成功");
                return true;
            } else {
                log.error("老师对应的课程信息删除失败");
                return false;
            }
        }
        return false;
    }

    /**
     * 添加老师的信息（不指定对应的学科）
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean insertSelective(Teacher teacher) {
        return teacherDao.insertSelective(teacher);
    }

    /**
     * 根据姓名查询老师的信息
     *
     * @param cname
     * @return
     */
    @Override
    public List<Teacher> selectByExample(String cname) {
        return teacherDao.selectByExample(cname);
    }

    /**
     * 查询老师和课程的信息
     *
     * @return
     */
    @Override
    public List<TeacherCourse> teachercourse() {
        return teacherDao.teachercourse();
    }

    /**
     * 插入老师和课程的信息
     *
     * @param teacherMiddle
     * @return
     */
    @Override
    public boolean insertTeacherMiddle(TeacherMiddle teacherMiddle) throws Demoxception {
        return teacherDao.insertTeacherMiddle(teacherMiddle);
    }

    /**
     * 修改老师的信息和对应的课程编号
     *
     * @param teacherMiddle
     * @return
     * @throws Demoxception
     */
    @Override
    public boolean updateByteacherBymiddle(TeacherMiddle teacherMiddle) throws Demoxception {
        return teacherDao.updateByteacherBymiddle(teacherMiddle);
    }

    /**
     * 根据性别查询老师对应的信息
     *
     * @param sex
     * @return
     */
    @Override
    public List<CourseTeacher> selectBysex(Integer sex) {
        return teacherDao.selectBysex(sex);
    }

    @Override
    public List<CourseTeacher> selectLikeBytname(String tname) {
        return teacherDao.selectLikeBytname(tname);
    }

}
