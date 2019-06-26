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
           List<Integer> result = new ArrayList<>();
           for (int i = 1;i<=4;i++){
               for(int j = 1;j<=4;j++){
                   for (int k = 1;k<=4;k++){
                       if (i != j&& j != k && i!= k){
                           result.add(i*100+j*10+k);
                       }
                   }
               }
           }
            System.out.print(result.size()+" ");
           for (int a = 0;a<result.size();a++){
               System.out.print(result.get(a)+" ");

           }
        }
}
