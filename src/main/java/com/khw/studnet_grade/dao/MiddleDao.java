package com.khw.studnet_grade.dao;

import com.khw.studnet_grade.entity.MiddleExample;
import com.khw.studnet_grade.mapper.MiddleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: MiddleDao
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/28  16:12
 */
@Component
public class MiddleDao {
    @Autowired
    MiddleMapper middleMapper;

    /**
     * 根据老师编号删除对应的老师信息
     *
     * @param tno
     * @return
     */
    public boolean deleteByExample(Integer[] tno) {
        int i = 0;
        if (tno == null && tno.length == 0) {
            return false;
        }
        //用数组初始化list
        List<Integer> integers = Arrays.asList(tno);
        //根据条件删除（delete from Score where sno=snos）读音yikezanpo
        MiddleExample example = new MiddleExample();
        MiddleExample.Criteria criteria = example.createCriteria();
        criteria.andTnoIn(integers);
        i = middleMapper.deleteByExample(example);
        return i > 0;
    }
}
