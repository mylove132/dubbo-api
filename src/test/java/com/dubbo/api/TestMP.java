package com.dubbo.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2019-06-25:10:36
 * Modify date: 2019-06-25:10:36
 */
public class TestMP {

    public static void main1(String[] args) {
        int[] mp = {1,5,2,3,7,9,21,18};
        int tmp;

        //循环数组的比对次数
        for (int i= 0;i < mp.length-1; i++){
            //循环每个数需要比对的次数
            for (int k = 0;k < mp.length - i -1;k++){
                if (mp[k] > mp[k+1]){
                    tmp = mp[k];
                    mp[k] = mp[k+1];
                    mp[k+1] = tmp;
                }
            }
        }
        for (int z = 0;z<mp.length;z++){
            System.out.print(mp[z]+" ");
        }
    }

        public static void main(String[] args) {
            List<Integer> list = new ArrayList<>();
            String zz = "abcabcdabcdeabab";
            for (int i = 0; i < zz.length(); i++) {
                if (i < zz.length() - 1) {
                    if ((zz.charAt(i + 1) - zz.charAt(i) != 1)) {
                        list.add(i);
                    }
                }
            }
            List<String> result = new ArrayList<>();
            result.add(zz.substring(0, list.get(0) + 1));
            result.add(zz.substring(list.get(list.size()-1)+1));
            for (int i = 0; i < list.size(); i++) {
                if (i < list.size() - 1) {
                    result.add(zz.substring(list.get(i) + 1, list.get(i + 1) + 1));
                }
            }
            for (String s : result){
                System.out.println(s);
            }
            java.util.Collections.sort(result, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return new Integer(o2.length()).compareTo(new Integer(o1.length()));
                }
            });
            System.out.println(result.get(0));
        }
}
