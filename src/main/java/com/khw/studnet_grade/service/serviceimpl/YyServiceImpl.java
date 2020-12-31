package com.khw.studnet_grade.service.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.khw.studnet_grade.service.YyService;
import com.khw.studnet_grade.util.APIResult;
import com.khw.studnet_grade.util.NewHttpCilentUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: YyServiceImpl
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/30  18:01
 */
@Slf4j
@Service
public class YyServiceImpl implements YyService {
    /**
     * 调用端外接口
     *
     * @param url
     * @param cityId
     * @param key
     * @return
     */
    @Override
    public APIResult selectByaya(String url, String cityId, String key) {
        long start = System.currentTimeMillis();
        log.info("请求url:{},请求参数：{},密钥：{}", url, cityId, key);
        Map<String, String> map = new HashMap();
        map.put("city", cityId);
        map.put("key", key);
        String s = NewHttpCilentUtil.doGet(url, map, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(s);
        String msg = jsonObject.getString("msg");
        String code = jsonObject.getString("code");
        APIResult result;
        if ("Sucess".equals(msg)) {
            String data = jsonObject.getString("data");
            result = new APIResult(code, "返回成功", data);
            log.info("调用接口返回参数:{}", result);
            return result;
        } else {
            result = new APIResult(code, "返回失败", null);
            log.info("调用接口返回参数:{}", result);
            return result;
        }
    }
}
