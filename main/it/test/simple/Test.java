package it.test.simple;

import java.util.*;

public class Test {

    // 3 5 7 -1 -1 2 4  5 2 4
    // 5 9 8 -1 -1 7 -1 -1 -1 -1 -1 6    9 6
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] split = str.split(" ");
        List<Integer> strList = new ArrayList<>();
        if(split.length<=1) {
            System.out.println(str);
        }
        //先找到叶子节点
        for (int i = 0; i < split.length; i++) {
            int left = (i + 1) * 2;
            int right = (i + 1) * 2 + 1;
            boolean b1 = false;
            boolean b2 = false;
            String s = split[i];
            if (left > split.length || Integer.parseInt(split[left-1]) == -1) {
                b1 = true;
            }
            if (right > split.length || Integer.parseInt(split[right-1]) == -1) {
                b2 = true;
            }
            if (b1 && b2 && Integer.parseInt(split[i])!= -1) {
                strList.add(Integer.parseInt(split[i]));
            }
        }
        Integer integer = strList.stream().min(Integer::compareTo).get();
        //然后找到最小的叶子节点的位置
        int pos = 0;
        for (int i = split.length-1; i >=0; i--) {
            if (Integer.parseInt(split[i]) == integer) {
                pos = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (pos > 1) {
            String s = split[pos];
            ans.add(Integer.parseInt(s));
            if (pos % 2 == 0) {
                //偶数的前一个是pos/2
                pos = pos / 2;
            } else {
                pos = (pos-1)/2;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(split[0]);
        for (int i = ans.size()-1; i >= 0; i--) {
            sb.append(" ").append(ans.get(i));
        }
        System.out.println(sb);


    }


}
