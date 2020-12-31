package com.khw.studnet_grade.dao;

import com.khw.studnet_grade.mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: CourseDao
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  14:38
 */
@Component
public class CourseDao {
    @Autowired
    CourseMapper courseMapper;
}
