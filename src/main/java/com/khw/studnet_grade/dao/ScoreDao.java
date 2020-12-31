package com.khw.studnet_grade.dao;

import com.khw.studnet_grade.entity.ScoreExample;
import com.khw.studnet_grade.mapper.ScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: ScoreDao
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  14:42
 */
@Component
public class ScoreDao {
    @Autowired
    ScoreMapper scoreMapper;

    /**
     * 根据学号删除学号对应的学生信息
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
        //根据条件删除（delete from Score where sno=snos）读音yikezanpo
        ScoreExample example = new ScoreExample();
        ScoreExample.Criteria criteria = example.createCriteria();
        criteria.andSnoIn(integers);
        i = scoreMapper.deleteByExample(example);
        return i > 0;
    }
}
