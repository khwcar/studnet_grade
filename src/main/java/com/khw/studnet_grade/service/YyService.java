package com.khw.studnet_grade.service;

import com.khw.studnet_grade.util.APIResult;

/**
 * @ClassName: YyService
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/30  18:00
 */

public interface YyService {
    APIResult selectByaya(String url, String cityId, String key);
}
