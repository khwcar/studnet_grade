package com.khw.studnet_grade.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.entity.Score;
import com.khw.studnet_grade.entity.Student;
import com.khw.studnet_grade.entity.pojo.*;
import com.khw.studnet_grade.service.StudentService;
import com.khw.studnet_grade.util.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/student")
public class StudentContrlloer {
    @Resource
    StudentService studentService;

    /**
     * 根据学号查询多个学生的信息
     *
     * @param sno
     * @return
     */
    @PostMapping("/selectByPrimaryKey")
    public APIResult<List<Student>> selectByPrimaryKey(@RequestBody Integer[] sno) {
        log.info("接收参数:{}", sno);
        long beginTime = System.currentTimeMillis();
        List<Student> students = studentService.selectByPrimaryKey(sno);
        APIResult result = new APIResult("0000", "成功", students);
        log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 只修改学生的信息
     *
     * @param student
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    public APIResult<String> updateByPrimaryKey(@RequestBody Student student) {
        log.info("接收参数:{}", JSON.toJSONString(student));
        long beginTime = System.currentTimeMillis();
        boolean b = studentService.updateByPrimaryKey(student);
        APIResult result = null;
        if (b) {
            result = new APIResult("0000", "成功", "修改本次数据成功,修改学生学号为：" + student.getSno());
            log.info("修改结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "修改本次数据失败,修改失败的学生学号为：" + student.getSno());
        log.error("修改结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 根据学号批量删除学生的信息
     *
     * @param sno
     * @return
     */
    @PostMapping("/deleteByExample")
    public APIResult<String> deleteByExample(@RequestBody Integer[] sno) throws Demoxception {
        log.info("接收参数:{}", sno);
        long beginTime = System.currentTimeMillis();
        boolean b = studentService.deleteByExample(sno);
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "删除本次数据成功,删除学号为：" + JSON.toJSONString(sno));
            log.info("删除结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "删除本次数据失败，失败删除学号为：" + JSON.toJSONString(sno));
        log.error("删除结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 插入学生的信息(无成绩)
     *
     * @param student
     * @return
     */
    @PostMapping("/insertSelective")
    public APIResult<String> insertSelective(@RequestBody Student student) {
        log.info("接收参数：{}", JSON.toJSONString(student));
        long beginTime = System.currentTimeMillis();
        boolean b = studentService.insert(student);
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "插入本次数据成功,插入姓名为：" + student.getSname());
            log.info("插入结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "插入本次数据失败，失败插入姓名为：" + student.getSname());
        log.error("插入结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 查询学生的个人信息（无成绩）
     *
     * @param sname
     * @return
     */
    @PostMapping("/selectByExample")
    public APIResult<List<Student>> selectByExample(String sname) {
        log.info("接收参数：{}", sname);
        long beginTime = System.currentTimeMillis();
        List<Student> students = studentService.selectByExample(sname);
        APIResult result;
        if (students != null || students.size() != 0) {
            result = new APIResult("0000", "成功", "本次查询数据成功,查询的结果为：" + students.toString());
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "本次查询数据失败，查询的结果为：" + null);
        log.error("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }


    /**
     * 查询学生对应的课程成绩
     *
     * @param p
     * @return
     */
    @GetMapping("/selectBysnoScores")
    public APIResult<List<StudentScore>> selectBysnoScores(Integer[] p) {
        log.info("接收参数：{}", p);
        long beginTime = System.currentTimeMillis();
        List<StudentScore> studentScores = studentService.selectBysnoScores(p);
        APIResult result;
        if (studentScores != null || studentScores.size() != 0) {
            result = new APIResult("0000", "成功", studentScores);
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", studentScores);
        log.error("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }


    /**
     * 查询学号对应该学号及格的学生信息
     *
     * @param sno
     * @return
     */
    @PostMapping("/SelectByAllStudentCourse")
    public APIResult SelectByAllStudentCourse(@RequestBody Integer[] sno) {
        List<Integer> integers = Arrays.asList(sno);
        List<StudentMark> studentMarks = studentService.SelectByAllStudentCourse(integers);
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (studentMarks != null) {
            result = new APIResult("0000", "成功", studentMarks);
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        } else {
            result = new APIResult("4444", "失败", null);
            log.info("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
    }

    /**
     * 根据学号和课程号查询对应课程的学生信息
     * {
     * "sno": 1,
     * "cno":[1,2,3]
     * }
     *
     * @param map
     * @return
     */
    @PostMapping("/selectBysnoBycno")
    public APIResult selectBysnoBycno(@RequestBody Map map) {
        List<StudentMark> studentMarks = studentService.selectBysnoBycno(map);
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (studentMarks != null && studentMarks.size() != 0) {
            result = new APIResult("0000", "成功", studentMarks);
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        } else {
            result = new APIResult("4444", "失败", null);
            log.info("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
    }

    /**
     * 查询所有学生的学生信息和对应的老师和课程成绩信息
     *
     * @return
     */
    @PostMapping("/selectAll.do")
    public APIResult selectAll() {
        List<StudentAll> studentAlls = studentService.selectAll();
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (studentAlls != null) {
            result = new APIResult("0000", "成功", studentAlls);
            log.info("查询结果成功，返回的结果集为：{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        } else {
            result = new APIResult("4444", "失败", null);
            log.info("查询结果失败，返回结果：{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
    }

    /**
     * 查询有成绩的学生的平均成绩
     *
     * @return
     */
    @PostMapping("/avgsCore")
    public APIResult avgsCore() {
        List<AvgScore> avgScores = studentService.avgsCore();
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (avgScores != null) {
            result = new APIResult("0000", "成功", avgScores);
            log.info("查询结果成功，返回的结果集为：{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        } else {
            result = new APIResult("4444", "失败", null);
            log.info("查询结果失败，返回结果：{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
    }

    /**
     * 根据姓名查询学生的平均成绩信息
     *
     * @param sname
     * @return
     */
    @GetMapping("/avgsBysname")
    public APIResult avgsBysname(String sname) {
        log.info("接收参数{}", sname);
        AvgScore avgScore = studentService.avgsBysname(sname);
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (avgScore != null) {
            result = new APIResult("0000", "成功", avgScore);
            log.info("查询结果成功，返回的结果集为：{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        } else {
            result = new APIResult("4444", "失败", null);
            log.info("查询结果失败，返回结果：{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
    }

    /**
     * {
     * "sno": 13,
     * "cno": 4,
     * "score": "80",
     * "tno": 4
     * }
     * 插入已经存在的学生的成绩
     *
     * @param score
     * @return
     * @throws Demoxception
     */
    @PostMapping("/insertBycnoBytno")
    public APIResult insertBycnoBytno(@RequestBody Score score) throws Demoxception {
        boolean b = studentService.insertBycnoBytno(score);
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "插入本次数据成功,插入学号为：" + score.getSno());
            log.info("插入结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "插入本次数据失败，失败插入学号为：" + score.getSno());
        log.error("插入结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * {
     * "student": {
     * "sname": "王明一",
     * "sage": "15",
     * "phone": "15383412589"
     * },
     * "scores": [
     * {
     * "cno": 1,
     * "score": "80",
     * "tno": 1
     * },
     * {
     * "cno": 2,
     * "score": "80",
     * "tno": 2
     * },
     * {
     * "cno": 3,
     * "score": "80",
     * "tno": 3
     * }
     * ]
     * }
     * 添加学生信息和对应的课程信息
     *
     * @param studentScores
     * @return
     * @throws Demoxception
     */
    @PostMapping("/insertBystudentBycore")
    public APIResult insertBystudentBycore(@RequestBody StudentScores studentScores) throws Demoxception {
        log.info("接收参数：{}", JSON.toJSONString(studentScores));
        boolean b = studentService.insertBystudentBycore(studentScores);
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "插入本次数据成功,插入姓名为：" + studentScores.getStudent().getSname());
            log.info("插入结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "插入本次数据失败，失败插入姓名为：" + studentScores.getStudent().getSname());
        log.error("插入结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 根据课程号，和分数的最大最小查该门课程的学生信息
     *
     * @param cno 课程号
     * @param min 最小
     * @param max 最大
     * @return
     */
    @GetMapping("/selectBycnoByscore")
    public APIResult selectBycnoByscore(Integer cno, double min, double max) {
        log.info("接收参数cno：{},min:{},max:{}", cno, min, max);
        CourseScore courseScore = studentService.selectBycnoByscore(cno, min, max);
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (courseScore != null) {
            result = new APIResult("0000", "成功", courseScore.toString());
            log.info("插入结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "查询的结果为：" + null);
        log.error("插入结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 实现分页查询（由于PageHelper不支持一对多的查询会出现分页问题，在下面改进）
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return
     */
    @PostMapping("/pagingQuery")
    public PageInfo pagingQuery(int pageNum, int pageSize) {
        Page page = PageHelper.startPage(pageNum, pageSize, true);
        List<StudentAll> studentAlls = studentService.selectAll();
        PageInfo<StudentAll> pageInfo = new PageInfo<StudentAll>(studentAlls);
        //总页数
        pageInfo.setPages(page.getPages());
        //总条数
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

    /**
     * 分页查询（每页数为0时，显示全部数据，在yml中配置）
     *
     * @param pageNum  页数
     * @param pageSize 每页存放的数量
     * @return
     */
    @PostMapping("/selectAllpage")
    public PageInfo selectAllpage(int pageNum, int pageSize) {
        log.info("当前页数：{},每页条数为：{}", pageNum, pageSize);
        Page page = PageHelper.startPage(pageNum, pageSize, true);
        List<StudentAll> studentAlls = studentService.selectAllpage();
        PageInfo<StudentAll> pageInfo = null;
        if (studentAlls != null) {
            log.info("查询成功，查询的结果为:{}", studentAlls);
            pageInfo = new PageInfo<StudentAll>(studentAlls);
            //总页数
            pageInfo.setPages(page.getPages());
            //总条数
            pageInfo.setTotal(page.getTotal());
            return pageInfo;
        } else {
            log.error("查询失败，查询的结果为：{}", studentAlls);
            return pageInfo;
        }
    }

    /**
     * {
     * "student": {
     * "sno": 1,
     * "sname": "张晓毛",
     * "sage": "16",
     * "phone": "15383412556"
     * },
     * "scores": [
     * {
     * "cno": 1,
     * "score": "80",
     * "tno": 1
     * },
     * {
     * "cno": 2,
     * "score": "80",
     * "tno": 2
     * }
     * ]
     * }
     * 修改学生的信息和对应课程的信息及课程对应老师的信息
     *
     * @param studentScores
     * @return
     * @throws Demoxception
     */
    @PostMapping("/updateBystudentByscore")
    public APIResult updateBystudentByscore(@RequestBody StudentScores studentScores) throws Demoxception {
        log.info("接收参数：{}", JSON.toJSONString(studentScores));
        boolean b = studentService.updateBystudentByscore(studentScores);
        long beginTime = System.currentTimeMillis();
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "修改本次数据成功,修改学号为：" + studentScores.getStudent().getSno());
            log.info("插入结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "修改本次数据失败，失败修改学号为：" + studentScores.getStudent().getSno());
        log.error("插入结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 模糊查询（根据姓）
     *
     * @param sname
     * @return
     */
    @PostMapping("/selectLikeBysname")
    public APIResult selectLikeBysname(String sname) {
        log.info("接收参数：{}", sname);
        long beginTime = System.currentTimeMillis();
        List<Student> students = studentService.selectLikeBysname(sname);
        APIResult result;
        if (students != null && students.size() != 0) {
            result = new APIResult("0000", "成功", students);
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", students);
        log.error("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 模糊查询（根据手机尾号）
     *
     * @param phone
     * @return
     */
    @PostMapping("/selectLikeByphone")
    public APIResult selectLikeByphone(String phone) {
        log.info("接收参数：{}", phone);
        long beginTime = System.currentTimeMillis();
        List<Student> students = studentService.selectLikeByphone(phone);
        APIResult result;
        if (students != null && students.size() != 0) {
            result = new APIResult("0000", "成功", students);
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", students);
        log.error("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 实现sno和cno一对一对应,根据sno和cno实现对信息
     *
     * @param sno
     * @param cno
     * @return
     */
    @PostMapping("/selectBysnoBycnoOne")
    public APIResult selectBysnoBycnoOne(Integer sno, Integer cno) {
        log.info("接收参数：{}", cno);
        long beginTime = System.currentTimeMillis();
        List<StudentMark> studentMarks = studentService.selectBysnoBycnoOne(sno, cno);
        APIResult result;
        if (studentMarks != null && studentMarks.size() != 0) {
            result = new APIResult("0000", "成功", studentMarks);
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", studentMarks);
        log.error("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }
}
