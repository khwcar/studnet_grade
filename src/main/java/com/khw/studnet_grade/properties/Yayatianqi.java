package com.khw.studnet_grade.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Yayatianqi
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/30  18:08
 */
@Data
@Component
@ConfigurationProperties(prefix = "yayatin")
public class Yayatianqi {
    private String url;
    private String city;
    private String key;
}
