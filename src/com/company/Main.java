package com.company;

import com.company.models.CountMap;
import com.company.models.CountMapImpl;

public class Main {

    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();
        System.out.println(map.size());
        System.out.println("=)");

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);

//        map.remove(10);

        System.out.println(map.getCount(5));
        System.out.println(map.getCount(6));
        System.out.println(map.getCount(10));
    }
}
