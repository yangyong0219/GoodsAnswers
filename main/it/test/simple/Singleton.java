package it.test.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Singleton {

    //饿汉式
//    private static Singleton instance = new Singleton();
//    private Singleton(){
//
//    }
//    public static Singleton getInstance(){
//        return instance;
//    }

    //线程安全的懒汉式
//    private static Singleton instance;
//    private Singleton(){}
//    public static synchronized Singleton getInstance(){
//        if (instance == null) {
//            return new Singleton();
//        }
//        return instance;
//    }

    //双所检测机制
//    private static
    public static void main(String[] args) {
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        Integer[] integers = a.toArray(new Integer[0]);

    }


}
