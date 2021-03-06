package it.test.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * 斐波拉契
 */
public class TestMath {

    public static int getFinx(int x) {
        if (x==1||x==2) return 1;
        return getFinx(x - 1) + getFinx(x - 2);
    }

    public static int getFin(int x) {
//        int[] note = new int[x+1];
//        if (x == 1 || x == 2) {
//            return 1;
//        }
//        if (note[x]!= 0) return note[x];
//        int value = getFin(x - 1) + getFin(x - 2);
//        note[x] = value;
//        return value;

        int[] note = new int[x+1];
        if (x ==1 || x == 2){
            return 1;
        }
        if (note[x]!=0) return note[x];
        int value = getFin(x-1)+getFin(x-2);
        note[x] = value;
        return value;
    }


    public static int getFinz(int x) {
//        if (x==1||x==2) return 1;
//        int[] values = new int[x+1];
//        values[1] = values[2] = 1;
//        for (int i = 3; i <= x; i++) {
//            values[i] = values[i - 1] + values[i - 2];
//        }
//        return values[x];
        if (x==1 || x ==2){
            return 1;
        }
        int[] dp = new int[x+1];
        for (int i = 3; i<= x; i++){
            dp[i] = dp[i-1]+dp[i-2];
        }
        return dp[x];
    }

    public static int getFiny(int x) {
//        if (x==1||x==2) return 1;
//        int pre=1 ,pree = 2;
//        int value = 0;
//        for (int i = 3; i <= x; i++) {
//            value = pre+pree;
//            pre = pree;
//            pree = value;
//        }
//        return value;

        if (x == 1 || x== 2){
            return 1;
        }
        int pre = 1;
        int next = 1;
        int value = 0;
        for (int i = 3; i<= x; i++){
            value = pre+next;
            pre = next;
            next = value;
        }
        return value;
    }

    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        for (int i = 1; i < 40; i++) {
//            System.out.println(getFiny(i));
//        }
//        long l1 = System.currentTimeMillis();
//        System.out.println((l1-l));

        List<String> a= new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            String s = System.currentTimeMillis() + "";
            a.add(s);
        }
        System.out.println(a.size());
        System.out.println(TestMath.getFiny(6));

    }
}
