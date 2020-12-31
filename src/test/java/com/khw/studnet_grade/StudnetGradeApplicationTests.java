package com.khw.studnet_grade;

import com.alibaba.fastjson.JSON;
import com.khw.studnet_grade.Exception.Demoxception;
import com.khw.studnet_grade.entity.*;
import com.khw.studnet_grade.entity.pojo.*;
import com.khw.studnet_grade.properties.Yayatianqi;
import com.khw.studnet_grade.service.StudentService;
import com.khw.studnet_grade.service.TeacherService;
import com.khw.studnet_grade.service.YyService;
import com.khw.studnet_grade.util.APIResult;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudnetGradeApplicationTests {
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    YyService yyService;
    @Autowired
    Yayatianqi yayatianqi;
    @Test
   void test1(){
        Integer[] sno = new Integer[]{1,2,3,4};
        List<Student> students = studentService.selectByPrimaryKey(sno);
        System.out.println(students);
    }
    @Test
    void test2(){
        Student student = new Student(1,"张张","20","15383439940");
        boolean b = studentService.updateByPrimaryKey(student);
        if (b){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }
    @Test
    void test3() throws Demoxception {
        Integer[] sno = new Integer[]{5};
        boolean b = studentService.deleteByExample(sno);
        if (b){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
    @Test
    void test4(){
        Student student = new Student(5,"汪汪","30","15415154545");
        boolean b = studentService.insertSelective(student);
        if (b){
            System.out.println("插入数据成功");
        }else {
            System.out.println("插入数据失败");
        }
    }
    @Test
    void test5(){
        String sname = "旺旺w";
        List<Student> students = studentService.selectByExample(sname);
        if (students==null||students.size()==0){
            System.out.println("没有该学号的学生");
        }else {
            System.out.println("该学号的学生为："+students);
        }
    }
    @Test
    void test6(){
        Integer[] snos = new Integer[]{1,2,3,4,5};
        List<StudentScore> studentScores = studentService.selectBysnoScores(snos);
        if (studentScores.size()==0){
            System.out.println("没有查到对性学号的学生");
        }else {
            System.out.println("查询到该学生的成绩："+studentScores);
        }
    }


    @Test
    void test9(){
        List<Integer> sno = new ArrayList<>();
        sno.add(1);
        sno.add(2);
        sno.add(3);
        List<StudentMark> studentMarks = studentService.SelectByAllStudentCourse(sno);
        System.out.println(studentMarks);
    }
    @Test
    void test10(){
        Map<String,Object> map = new HashMap<>();
        map.put("sno",1);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        map.put("cno",list);
        List<StudentMark> studentMarks = studentService.selectBysnoBycno(map);
        System.out.println(studentMarks);
    }
    @Test
    void test11(){
        List list = new ArrayList();
        list.add(10);
        list.add(1);
        list.add(2);
        List<Student> students = studentService.selectBysno(list);
        System.out.println("输出结果为："+students);
    }
    @Test
    void test12(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        List<Student> students = studentService.selectBysnos(list);
        System.out.println("测试结果为："+students);
    }
    @Test
    void test13() throws Demoxception {
        Teacher teacher = new Teacher();
        teacher.setTname("哈哈哈");
        teacher.setAge(35);
        teacher.setSex(1);

        Middle middle = new Middle();
        middle.setCno(1);
        TeacherMiddle teacherMiddle = new TeacherMiddle();
        teacherMiddle.setMiddles(middle);
        teacherMiddle.setTeachers(teacher);
        boolean b = teacherService.insertTeacherMiddle(teacherMiddle);
        if (b){
            System.out.println("插入成功");
        }else {
            System.out.println("插入失败");
        }

    }
    @Test
    void test14() throws Demoxception {
        Score score = new Score();
        score.setSno(9);
        score.setCno(3);
        score.setScore("90");
        score.setTno(3);
        boolean b = studentService.insertBycnoBytno(score);
        if (b){
            System.out.println("插入成绩成功");
        }else {
            System.out.println("插入成绩失败");
        }
    }
    @Test
    void test15() throws Demoxception {
        StudentScores studentScores = new StudentScores();
        Student student = new Student();
        student.setSname("王二丽");
        student.setPhone("12345468924");
        student.setSage("15");
        Score score = new Score();
        score.setCno(1);
        score.setScore("100");
        score.setTno(1);
        Score score1 = new Score();
        score1.setCno(2);
        score1.setScore("80");
        score1.setTno(2);
        Score score2 = new Score();
        score2.setCno(3);
        score2.setScore("90");
        score2.setTno(3);
        List<Score> scores = new ArrayList<>();
        scores.add(score);
        scores.add(score1);
        scores.add(score2);
        studentScores.setScores(scores);
        studentScores.setStudent(student);
        boolean b = studentService.insertBystudentBycore(studentScores);
        if (b){
            System.out.println("成功");
        }else {
            System.out.println("失败");
        }
    }

    /**
     * 测试修该
     * @throws Demoxception
     */
    @Test
    void test16() throws Demoxception {
        TeacherMiddle teacherMiddle = new TeacherMiddle();
        Teacher t = new Teacher(1, "张大风", 40, 1);
        teacherMiddle.setTeachers(t);
        teacherMiddle.setMiddles(new Middle(1,t.getTno()));
        boolean b = teacherService.updateByteacherBymiddle(teacherMiddle);
        if (b){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    /**
     * 测试修改
     */
    @Test
    void test17() throws Demoxception {
        Student student = new Student(1,"张大傻","16","15278956148");
        Score score = new Score(student.getSno(),1,"80",14);
        List<Score> scores = new ArrayList<>();
        scores.add(score);
        StudentScores studentScores = new StudentScores();
        studentScores.setStudent(student);
        studentScores.setScores(scores);
        boolean b = studentService.updateBystudentByscore(studentScores);
        if (b){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }
    }

    /**
     * 测试连接yy天气接口
     */
    @Test
    void test18(){
        APIResult s = yyService.selectByaya(yayatianqi.getUrl(), yayatianqi.getCity(), yayatianqi.getKey());
        System.out.println(JSON.toJSONString(s));
        Object data2 = s.getData();
        System.out.println("----\n"+data2);
    }
}
