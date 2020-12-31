package com.khw.studnet_grade.dao;

import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.entity.Score;
import com.khw.studnet_grade.entity.Student;
import com.khw.studnet_grade.entity.StudentExample;
import com.khw.studnet_grade.entity.pojo.*;
import com.khw.studnet_grade.mapper.StudentMapper;
import com.khw.studnet_grade.mapper.extend.StudentScoreMapper;
import com.khw.studnet_grade.mapper.extend.TesMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class StudentDao {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentScoreMapper studentScoreMapper;
    @Autowired
    private TesMapper tesMapper;

    /**
     * 根据学号查询学生的信息
     *
     * @param sno
     * @return
     */
    public Student selectByPrimaryKey(Integer sno) {
        return studentMapper.selectByPrimaryKey(sno);
    }

    /**
     * 修改学生的信息（校验手机号）
     *
     * @param student
     * @return
     */
    public boolean updateByPrimaryKey(Student student) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])\" +\n" +
                "                \"|(18[0-9])|(19[8,9]))\\\\d{8}$";
        if (student.getPhone().trim().length() != 11) {
            log.error("你输入的手机号不符合11位:{}", student.getPhone());
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(student.getPhone());
            boolean isMatch = m.matches();
            if (isMatch) {
                log.error("你输入的手机号不符合手机号的格式:{}", student.getPhone());
                return false;
            } else {
                log.info("你输入的手机号正确:{}", student.getPhone());
                int i = studentMapper.updateByPrimaryKey(student);
                return i > 0;
            }
        }
    }

    /**
     * 批量删除学生的信息
     *
     * @param sno
     * @return
     */
    public boolean deleteByExample(Integer[] sno) {
        int i = 0;
        if (sno == null && sno.length == 0) {
            return false;
        }
        //用数组初始化list
        List<Integer> integers = Arrays.asList(sno);
        //根据条件删除（delete from student where sno=snos）读音yikezanpo
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andSnoIn(integers);
        i = studentMapper.deleteByExample(example);
        return i > 0;
    }

    /**
     * 插入学生数据(无成绩)
     *
     * @param student
     * @return
     */
    public boolean insert(Student student) {
        int i = studentMapper.insert(student);
        return i > 0;
    }


    /**
     * 根据姓名查询该学生
     *
     * @param sname
     * @return
     */
    public List<Student> selectByExample(String sname) {
        StudentExample example = new StudentExample();
        StudentExample.Criteria criteria = example.createCriteria();
        criteria.andSnameEqualTo(sname);
        List<Student> students = studentMapper.selectByExample(example);
        return students;
    }

    /**
     * 根据学号获取该学生所选择的课程和成绩
     *
     * @param sno
     * @return
     */
    public StudentScore selectBysnoScores(Integer sno) {
        return studentScoreMapper.selectBysnoScores(sno);
    }

    /**
     * 根据老师的名字查询对应该老师的学生
     *
     * @param tname
     * @return
     */
    public TeacherStudent SelectTeacherBysname(String tname) {
        return studentScoreMapper.SelectTeacherBysname(tname);
    }

    /**
     * 查询全部学生的所有课程成绩以及对应的老师信息
     *
     * @return
     */
    public List<StudentAll> selectAll() {
        List<StudentAll> studentAlls = studentScoreMapper.selectAll();
        return studentAlls;
    }

    /**
     * 查询学生的平均成绩（对学生姓名进行分组）
     *
     * @return
     */
    public List<AvgScore> avgsCore() {
        List<AvgScore> avgScores = studentScoreMapper.avgsCore();
        return avgScores;
    }

    /**
     * 根据姓名查询对应学生的平均成绩
     *
     * @param sname
     * @return
     */
    public AvgScore avgsBysname(String sname) {
        AvgScore avgScores = studentScoreMapper.avgsBysname(sname);
        return avgScores;
    }

    /**
     * 录入学生的各科成绩
     *
     * @param score
     * @return
     */
    @Transactional(rollbackFor = Demoxception.class)
    public Integer insertBycnoBytno(Score score) throws Demoxception {
        return studentScoreMapper.insertBycnoBytno(score);
    }

    /**
     * 插入学生信息
     *
     * @param student
     * @return
     */
    public Integer insertBystudent(Student student) {
        return studentScoreMapper.insertBystudent(student);
    }

    /**
     * 插入课程信息
     *
     * @param score
     * @return
     */
    public Integer inserByScore(Score score) {
        return studentScoreMapper.inserByScore(score);
    }


    /**
     * 根据课程号，和分数的最大最小查该门课程的学生信息
     *
     * @param map
     * @return
     */
    public CourseScore selectBycnoByscore(Map map) {
        return studentScoreMapper.selectBycnoByscore(map);
    }

    /**
     * 分页查询
     *
     * @return
     */
    public List<StudentAll> selectAllpage() {
        return studentScoreMapper.selectAllpage();
    }

    /**
     * 修改学生的信息
     *
     * @param student
     * @return
     */
    public Integer updateBystudent(Student student) {
        return studentScoreMapper.updateBystudent(student);
    }

    /**
     * 修改成绩信息
     *
     * @param score
     * @return
     */
    public Integer updateByscore(Score score) {
        return studentScoreMapper.updateByscore(score);
    }

    /**
     * 模糊查询（根据姓查询）
     *
     * @param sname
     * @return
     */
    public List<Student> selectLikeBysname(String sname) {
        List<Student> students = studentScoreMapper.selectLikeBysname(sname);
        return students;
    }

    /**
     * 模糊查询（根据手机尾号后4位）
     *
     * @param phone
     * @return
     */
    public List<Student> selectLikeByphone(String phone) {
        List<Student> students = studentScoreMapper.selectLikeByphone(phone);
        return students;
    }

    /**
     * 根据学号课程号查询对应学生的信息
     *
     * @param map
     * @return
     */
    public List<StudentMark> selectBysnoBycnoOne(Map map) {
        return studentScoreMapper.selectBysnoBycnoOne(map);
    }
}
