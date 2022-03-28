package com.hungteen.pvz.utils;

import java.util.Arrays;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-03-27 21:52
 **/
public class StringUtil {

    private static final List<String> ROMAN_NUMBERS = Arrays.asList("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");

    public static String toSecond(int tick){
        return String.format("%.1f", tick * 1.0F / 20);
    }

    public static String getRomanString(int num){
        if(num > 0 && num <= 10){
            return ROMAN_NUMBERS.get(num - 1);
        }
        return "";
    }
}
