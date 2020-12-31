package com.khw.studnet_grade.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: VerifyPhone
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/31  13:59
 */
public class VerifyPhone {
    String regex = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])\" +\n" +
            "                \"|(18[0-9])|(19[8,9]))\\\\d{8}$";

    public boolean Verifyphones(String phone) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
        boolean isMatch = m.matches();
        return isMatch;
    }
}
