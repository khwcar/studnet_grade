package com.khw.studnet_grade.service.serviceimpl;

import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.dao.RallyDao;
import com.khw.studnet_grade.dao.ScoreDao;
import com.khw.studnet_grade.dao.StudentDao;
import com.khw.studnet_grade.entity.Score;
import com.khw.studnet_grade.entity.Student;
import com.khw.studnet_grade.entity.pojo.*;
import com.khw.studnet_grade.service.StudentService;
import com.khw.studnet_grade.util.VerifyPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    RallyDao rallyDao;
    VerifyPhone verifyPhone = new VerifyPhone();

    /**
     * 根据学号批量查询学生信息
     *
     * @param sno
     * @return
     */
    @Override
    public List<Student> selectByPrimaryKey(Integer[] sno) {
        List<Student> students = new ArrayList<>();
        for (Integer snos : sno) {
            Student student = studentDao.selectByPrimaryKey(snos);
            students.add(student);
        }
        return students;
    }

    /**
     * 修改学生的信息
     *
     * @param student
     * @return
     */
    @Override
    public boolean updateByPrimaryKey(Student student) {
        return studentDao.updateByPrimaryKey(student);
    }

    /**
     * 删除学生的信息（删除学生信息时要将对应的学生信息一块删除）
     *
     * @param sno
     * @return
     */
    @Transactional(rollbackFor = Demoxception.class)
    @Override
    public boolean deleteByExample(Integer[] sno) throws Demoxception {
        boolean b = studentDao.deleteByExample(sno);
        boolean back = false;
        if (b) {
            log.info("学生表中删除成功");
            boolean b1 = scoreDao.deleteByExample(sno);
            if (b1) {
                log.info("成绩表中删除成功");
                return true;
            } else {
                List<StudentScore> studentScores = null;
                //判断成绩表sno对应的课程是否有成绩，如果有成绩如何删除，无成绩如何删除
                for (Integer snos : sno) {
                    StudentScore score = studentDao.selectBysnoScores(snos);
                    back = score != null;
                }
                if (back) {
                    log.info("该学生删除失败");
                    throw new Demoxception("成绩表删除失败回滚到开始状态");
                } else {
                    log.info("删除成功,且该学生没有成绩");
                    return true;
                }
            }
        } else {
            log.error("该学生删除失败，可能没有删除该学生的学号");
            return false;
        }
    }

    /**
     * 添加学生的信息
     *
     * @param student
     * @return
     */
    @Override
    public boolean insert(Student student) {
        if (null == student) {
            return false;
        }
        //校验手机号是否满足11位
        if (student.getPhone().trim().length() != 11) {
            log.error("你输入的手机号不符合11位:{}", student.getPhone());
            return false;
        } else {
            //校验手机号
            boolean verifyphones = verifyPhone.Verifyphones(student.getPhone());
            if (verifyphones) {
                log.error("你输入的手机号不符合手机号的格式:{}", student.getPhone());
                return false;
            } else {
                log.info("你输入的手机号正确:{}", student.getPhone());
                //插入数据
                return studentDao.insert(student);
            }
        }
    }

    /**
     * 根据学生的姓名批量查询用户的信息
     *
     * @param sname
     * @return
     */
    @Override
    public List<Student> selectByExample(String sname) {
        return studentDao.selectByExample(sname);

    }

    /**
     * 根据学号查询成绩
     *
     * @param sno
     * @return
     */
    @Override
    public List<StudentScore> selectBysnoScores(Integer[] sno) {
        List<StudentScore> studentScores = new ArrayList<>();
        //遍历学号
        for (Integer snos : sno) {
            //根据学号查询所有学生的成绩
            StudentScore studentScore = studentDao.selectBysnoScores(snos);
            studentScores.add(studentScore);
        }
        return studentScores;
    }

    /**
     * 底层使用map接收数据，查询学号中及格的学生
     *
     * @param sno
     * @return
     */
    @Override
    public List<StudentMark> SelectByAllStudentCourse(List sno) {
        return rallyDao.SelectByAllStudentCourse(sno);
    }

    @Override
    public List<StudentMark> selectBysnoBycno(Map map) {
        return rallyDao.selectBysnoBycno(map);

    }

    @Override
    public List<Student> selectBysno(List list) {
        return rallyDao.selectBysno(list);
    }

    @Override
    public List<Student> selectBysnos(List list) {
        return rallyDao.selectBysnos(list);
    }

    /**
     * 查询全部学生的全部信息
     *
     * @return
     */
    @Override
    public List<StudentAll> selectAll() {
        return studentDao.selectAll();
    }

    /**
     * 查询学生的平均成绩
     *
     * @return
     */
    @Override
    public List<AvgScore> avgsCore() {
        return studentDao.avgsCore();
    }

    /**
     * 根据学生姓名查询对应学生的信息
     *
     * @param sname
     * @return
     */
    @Override
    public AvgScore avgsBysname(String sname) {
        return studentDao.avgsBysname(sname);
    }

    /**
     * 插入学生的对应的课程的成绩
     *
     * @param score
     * @return
     * @throws Demoxception
     */
    @Override
    public boolean insertBycnoBytno(Score score) throws Demoxception {
        //获取用户学号
        Integer sno = score.getSno();
        List<Integer> cno = new ArrayList<>();
        cno.add(score.getCno());
        //组装参数sno和cno
        Map map = new HashMap();
        map.put("cno", cno);
        map.put("sno", sno);
        Student student = studentDao.selectByPrimaryKey(sno);
        log.info("拿到的用户信息为：{}", student);
        //判断该学生信息信息是否存在
        if (student == null) {
            log.error("没有该学号的学生，接收学号：{}", sno);
            return false;
        }
        log.info("查询到学号为：{}的学生", sno);
        List<StudentMark> studentMarks = rallyDao.selectBysnoBycno(map);
        //判断该学号该课程号对应的学生信息是否存在
        if (studentMarks.size() != 0) {
            log.error("学号为：{}的，课程号为：{}的学生已经有该门学科的成绩", sno, cno);
            return false;
        }
        log.info("学号为：{}，课程号为：{}的学生可以录入学生信息", sno, cno);
        //检查传进来的成绩是否为空
        if (score.getScore() == null) {
            throw new Demoxception("你录入的学生没有对应的成绩");
        }
        log.info("要录入的学生成绩为：{},老师的编号为：{}", score.getScore(), score.getTno());
        //插入学生和课程信息
        Integer integer = studentDao.insertBycnoBytno(score);
        if (integer > 0) {
            //数据插入成功
            log.info("成功操作插入条数：{}条", integer);
            return true;
        } else {
            //数据插入失败
            log.error("失败操作，请检查数据库");
            return false;
        }
    }

    /**
     * 添加学生增加学生的信息和成绩
     *
     * @param studentScores
     * @return
     * @throws Demoxception
     */
    @Override
    public boolean insertBystudentBycore(StudentScores studentScores) throws Demoxception {
        Student student = studentScores.getStudent();
        //判断手机号是否为11位
        if (student.getPhone().trim().length() != 11) {
            log.error("你输入的手机号不符合11位:{}", student.getPhone());
            return false;
        }
        //检查手机号的输入格式
        boolean verifyphones = verifyPhone.Verifyphones(studentScores.getStudent().getPhone());
        if (verifyphones) {
            log.error("你输入的手机号不符合手机号的格式:{}", student.getPhone());
            return false;
        }
        log.info("你输入的手机号正确:{}", student.getPhone());
        //根据学号查询该学生是否存在
        Student student1 = studentDao.selectByPrimaryKey(student.getSno());
        int sum = 0;
        //学生信息已经存在
        if (student1 != null) {
            log.info("用户已经存在，获得用户的信息为：{}", student1);
            return false;
        }
        //学生信息不存在，插入学生信息
        Integer integer = studentDao.insertBystudent(student);
        //插入信息成功，准备插入成绩
        if (integer > 0) {
            log.info("学生表插入成功条数为：{}，插入学生的名字为：{}", integer, student.getSname());
            //获取该学生要录入成绩的集合
            List<Score> scores = studentScores.getScores();
            //遍历多门课程的成绩
            for (Score score : scores) {
                //将返回的sno返回在插入
                score.setSno(student.getSno());
                //插入到成绩表中
                Integer integer1 = studentDao.inserByScore(score);
                //记录插入成功条数
                if (integer1 > 0) {
                    sum++;
                }
            }
            //如果插入成功的条数和传进来的成绩个数相等
            if (scores.size() == sum) {
                log.info("成绩表插入成功条数为：{}", sum);
                return true;
            }
            //成绩插入失败
            log.error("成绩表插入失败：{}");
            throw new Demoxception("成绩表插入失败，事件回滚");
        }
        //学生表插入失败
        log.info("学生表插入失败，插入学生的名字为：{}", student.getSname());
        return false;
    }


    /**
     * 查询某门课程在某个分数段的学生信息
     *
     * @param cno
     * @param min
     * @param max
     * @return
     */
    @Override
    public CourseScore selectBycnoByscore(Integer cno, double min, double max) {
        Map map = new HashMap();
        map.put("cno", cno);
        map.put("min", min);
        map.put("max", max);
        return studentDao.selectBycnoByscore(map);
    }

    /**
     * 查询全部学生的信息并且实现分页查询
     *
     * @return
     */
    @Override
    public List<StudentAll> selectAllpage() {
        return studentDao.selectAllpage();
    }

    /**
     * 修改学生和对应的多个课程的信息以及对应的老师
     *
     * @param studentScores
     * @return
     * @throws Demoxception
     */
    @Override
    public boolean updateBystudentByscore(StudentScores studentScores) throws Demoxception {
        Student student = studentScores.getStudent();
        //校验手机号是否11位
        if (student.getPhone().trim().length() != 11) {
            log.error("你输入的手机号不符合11位:{}", student.getPhone());
            return false;
        }
        boolean verifyphones = verifyPhone.Verifyphones(studentScores.getStudent().getPhone());
        //校验手机号是否合法
        if (verifyphones) {
            log.error("你输入的手机号不符合手机号的格式:{}", student.getPhone());
            return false;
        }
        log.info("你输入的手机号正确:{}", student.getPhone());
        //修改学生的信息
        Integer integer1 = studentDao.updateBystudent(student);
        int sum = 0;
        //修改成功
        if (integer1 > 0) {
            log.info("修改学生表成功，成功条数为：{}条", integer1);
            List<Score> scores = studentScores.getScores();
            //遍历用户的各科成绩
            for (Score score : scores) {
                score.setSno(student.getSno());
                //修改各科成绩
                Integer integer = studentDao.updateByscore(score);
                if (integer > 0) {
                    sum++;
                }
            }
            //成功的次数和传入成绩的长度相等
            if (sum == scores.size()) {
                log.info("修改成绩表成功,修改成功条数：{}条", sum);
                return true;
            }
            //成绩表修改失败
            log.error("修改成绩表失败，事件将会回到最开始的状态");
            throw new Demoxception("修改成绩表失败，事件回滚到开始状态");

        }
        //学生表修改失败
        log.error("修改学生表失败，请检查格式,或者要修改的学生信息是否存在！");
        return false;
    }

    /**
     * 模糊查询（根据姓）
     *
     * @param sname
     * @return
     */
    @Override
    public List<Student> selectLikeBysname(String sname) {
        return studentDao.selectLikeBysname(sname);
    }

    /**
     * 模糊查询（手机号）
     *
     * @param phone
     * @return
     */
    @Override
    public List<Student> selectLikeByphone(String phone) {
        return studentDao.selectLikeByphone(phone);
    }

    /**
     * 根据学号课程号查询对应学生的信息
     *
     * @param sno
     * @param cno
     * @return
     */
    @Override
    public List<StudentMark> selectBysnoBycnoOne(Integer sno, Integer cno) {
        //将学号和课程号封装起来
        Map map = new HashMap();
        map.put("sno", sno);
        map.put("cno", cno);
        List<StudentMark> studentMarks = studentDao.selectBysnoBycnoOne(map);
        return studentMarks;
    }
}
