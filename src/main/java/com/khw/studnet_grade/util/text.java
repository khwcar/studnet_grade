package com.khw.studnet_grade.util;

/**
 * @ClassName: text
 * @Description: TODO
 * @author: kanghongwei
 * @date: 2020/12/29  16:49
 */
public class text {
    public static void main(String[] args) {
        double a = 0.10;
        double b = 0.20 - 0.10;
        Double a1 = 0.09;
        Double b1 = 0.20 - 0.11;
        double ttt = 0.00001;
        if ((b1 - a1) < 0.01) {
            System.out.println("true");
        }
        System.out.println(a1.equals(b1));
        System.out.println(a == b);
        float c = 0.2f;
        float d = 0.2f;
        System.out.println(c == d);
    }
}
