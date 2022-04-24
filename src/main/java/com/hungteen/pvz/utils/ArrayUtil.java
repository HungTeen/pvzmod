package com.hungteen.pvz.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: pvzmod-1.18.x
 * @author: HungTeen
 * @create: 2022-04-22 15:03
 **/
public class ArrayUtil {

    public static List<Integer> getAverageArray(int len, int from, int to){
        final ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < len; ++ i){
            list.add(from + (to - from) / (len - 1) * i);
        }
        return list;
    }

    public static List<Float> getAverageArray(int len, float from, float to){
        final ArrayList<Float> list = new ArrayList<>();
        for(int i = 0; i < len; ++ i){
            list.add(from + (to - from) / (len - 1) * i);
        }
        return list;
    }

}
