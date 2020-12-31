package com.khw.studnet_grade.controller;

import com.alibaba.fastjson.JSON;
import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.entity.Teacher;
import com.khw.studnet_grade.entity.pojo.CourseTeacher;
import com.khw.studnet_grade.entity.pojo.TeacherCourse;
import com.khw.studnet_grade.entity.pojo.TeacherMiddle;
import com.khw.studnet_grade.service.TeacherService;
import com.khw.studnet_grade.util.APIResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: TeacherContrlloer
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  15:35
 */
@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherContrlloer {
    @Autowired
    TeacherService teacherService;

    /**
     * 根据学号查询多个学生的信息
     *
     * @param tno
     * @return
     */
    @PostMapping("/selectByPrimaryKey")
    public APIResult selectByPrimaryKey(@RequestBody Integer[] tno) {
        log.info("接收参数:{}", tno);
        long beginTime = System.currentTimeMillis();
        List<Teacher> students = teacherService.selectByPrimaryKey(tno);
        APIResult result = new APIResult("0000", "成功", students);
        log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 只修改老师的信息（不修改对应的课表）
     *
     * @param teacher
     * @return
     */
    @PostMapping("/updateByPrimaryKey")
    public APIResult updateByPrimaryKey(@RequestBody Teacher teacher) {
        log.info("接收参数:{}", JSON.toJSONString(teacher));
        long beginTime = System.currentTimeMillis();
        boolean b = teacherService.updateByPrimaryKey(teacher);
        APIResult result = null;
        if (b) {
            result = new APIResult("0000", "成功", "修改本次数据成功,修改老师的编号为：" + teacher.getTno());
            log.info("修改结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "修改本次数据失败,修改失败的老师编号为：" + teacher.getTno());
        log.error("修改结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 根据老师工号批量删除老师的信息
     *
     * @param tno
     * @return
     */
    @PostMapping("/deleteByExample")
    public APIResult deleteByExample(@RequestBody Integer[] tno) {
        log.info("接收参数:{}", tno);
        long beginTime = System.currentTimeMillis();
        boolean b = teacherService.deleteByExample(tno);
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "删除本次数据成功,删除学号为：" + JSON.toJSONString(tno));
            log.info("删除结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "删除本次数据失败，失败删除学号为：" + JSON.toJSONString(tno));
        log.error("删除结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 插入老师的信息(无对应的课程)
     *
     * @param teacher
     * @return
     */
    @PostMapping("/insertSelective")
    public APIResult insertSelective(@RequestBody Teacher teacher) {
        log.info("接收参数：{}", JSON.toJSONString(teacher));
        long beginTime = System.currentTimeMillis();
        boolean b = teacherService.insertSelective(teacher);
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "插入本次数据成功,插入姓名为：" + teacher.getTname());
            log.info("插入结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "插入本次数据失败，失败插入姓名为：" + teacher.getTname());
        log.error("插入结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 查询老师的个人信息（无课程）
     *
     * @param tname
     * @return
     */
    @PostMapping("/selectByExample")
    public APIResult selectByExample(String tname) {
        log.info("接收参数：{}", tname);
        long beginTime = System.currentTimeMillis();
        List<Teacher> teachers = teacherService.selectByExample(tname);
        APIResult result;
        if (teachers != null || teachers.size() != 0) {
            result = new APIResult("0000", "成功", "本次查询数据成功,查询的结果为：" + teachers.toString());
            log.info("查询结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "本次查询数据失败，查询的结果为：" + null);
        log.error("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 查询课程对应的老师信息
     *
     * @return
     */
    @PostMapping("/teachercourse")
    public APIResult teachercourse() {
        long beginTime = System.currentTimeMillis();
        List<TeacherCourse> teachercourse = teacherService.teachercourse();
        APIResult result;
        if (teachercourse != null) {
            result = new APIResult("0000", "成功", "查询成功，本次查询的结果为：" + teachercourse);
            log.info("查询结果成功，返回结果:{},耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "本次查询数据失败，查询的结果为：" + null);
        log.error("查询结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * {
     * "teachers": {
     * "tname": "王呜呜",
     * "age": 45,
     * "sex": 0
     * },
     * "middles": {
     * "cno": 1
     * }
     * }
     * 插入老师和对应的课程的信息
     *
     * @param teacherMiddle
     * @return
     * @throws Demoxception
     */
    @PostMapping("/insertTeacherMiddle")
    public APIResult insertTeacherMiddle(@RequestBody TeacherMiddle teacherMiddle) throws Demoxception {
        log.info("接收参数：{}", teacherMiddle);
        long beginTime = System.currentTimeMillis();
        boolean b = teacherService.insertTeacherMiddle(teacherMiddle);
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "插入本次数据成功,插入姓名为：" + teacherMiddle.getTeachers().getTname());
            log.info("插入结果成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "插入本次数据失败，失败插入姓名为：" + teacherMiddle.getTeachers().getTname());
        log.error("插入结果失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * {
     * "teachers": {
     * "tno": 12,
     * "tname": "王呜呜",
     * "age": 45,
     * "sex": 0
     * },
     * "middles": {
     * "cno": 1
     * }
     * }
     * 修改老师的信息和对应课程的信息
     *
     * @param teacherMiddle
     * @return
     */
    @PostMapping("/updateByteacherBymiddle")
    public APIResult updateByteacherBymiddle(@RequestBody TeacherMiddle teacherMiddle) throws Demoxception {
        log.info("接收参数：{}", teacherMiddle);
        long beginTime = System.currentTimeMillis();
        boolean b = teacherService.updateByteacherBymiddle(teacherMiddle);
        APIResult result;
        if (b) {
            result = new APIResult("0000", "成功", "修改本次数据成功，修改老师的姓名为：" + teacherMiddle.getTeachers().getTname());
            log.info("修改成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", "修改本次数据失败，失败修改老师姓名为：" + teacherMiddle.getTeachers().getTname());
        log.error("修改失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 根据性别查询对应老师的信息（1：男2：女）
     *
     * @param sex
     * @return
     */
    @PostMapping("/selectBysex")
    public APIResult selectBysex(Integer sex) {
        log.info("接收参数：{}", sex);
        long beginTime = System.currentTimeMillis();
        List<CourseTeacher> courseTeachers = teacherService.selectBysex(sex);
        APIResult result;
        if (courseTeachers != null && courseTeachers.size() != 0) {
            result = new APIResult("0000", "成功", courseTeachers);
            log.info("查询成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", null);
        log.error("查询失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }

    /**
     * 模糊查询根据姓名查询对应的老师的信息
     *
     * @param tname
     * @return
     */
    @PostMapping("/selectLikeBytname")
    public APIResult selectLikeBytname(String tname) {
        log.info("接收参数：{}", tname);
        long beginTime = System.currentTimeMillis();
        List<CourseTeacher> courseTeachers = teacherService.selectLikeBytname(tname);
        APIResult result = null;
        if (courseTeachers != null && courseTeachers.size() != 0) {
            result = new APIResult("0000", "成功", courseTeachers);
            log.info("查询成功，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
            return result;
        }
        result = new APIResult("4444", "失败", null);
        log.error("查询失败，返回结果:{}，耗时：【{}】毫秒", JSON.toJSONString(result), System.currentTimeMillis() - beginTime);
        return result;
    }
}
