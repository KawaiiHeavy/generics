package com.company.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

public class CountMapTest {

    @Test
    public void addingIntegersGettingCount() {
        CountMap<Integer> countMap = new CountMapImpl();

        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);
        Assertions.assertEquals(3, countMap.getCount(10));
        Assertions.assertEquals(2, countMap.getCount(5));
        Assertions.assertEquals(1, countMap.getCount(6));
    }

    @Test
    public void size() {
        CountMap<Integer> countMap = new CountMapImpl();

        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);
        countMap.add(1);
        Assertions.assertEquals(4, countMap.size());
    }

    @Test
    public void removeFromCountMap() {
        CountMap<Integer> countMap = new CountMapImpl();

        countMap.remove(1);
        Assertions.assertEquals(0, countMap.size());
        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.remove(10);
        Assertions.assertEquals(1, countMap.size());
    }

    @Test
    public void addFromAnotherCountMap() {
        CountMap<Integer> countMap = new CountMapImpl();

        countMap.add(10);
        countMap.add(1);

        CountMap<Integer> input = new CountMapImpl();
        input.add(10);
        input.add(10);
        input.add(5);

        countMap.addAll(input);
        Assertions.assertEquals(3, countMap.size());
        Assertions.assertEquals(3, countMap.getCount(10));
        Assertions.assertEquals(1, countMap.getCount(1));
        Assertions.assertEquals(1, countMap.getCount(5));
    }

    @Test
    public void writeToNewMap() {
        CountMap<Number> countMap = new CountMapImpl();

        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);
        countMap.add(1);
        Map<Number, Integer> map = countMap.toMap();

        // При вызове toMap создается TreeMap, который сохраняет порядок добавления
        // (просто чтобы было проще сравнить все значения)
        for (Map.Entry<? extends Number, Integer> entry : map.entrySet()) {
            Assertions.assertEquals(countMap.getCount(entry.getKey()), entry.getValue());
        }

    }

    @Test
    public void writeToReceivedMap() {
        CountMap<Integer> countMap = new CountMapImpl();

        Map<Integer, Integer> map = new TreeMap<>();

        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);
        countMap.add(1);

        countMap.toMap(map);

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Assertions.assertEquals(countMap.getCount(entry.getKey()), entry.getValue());
        }
    }

    @Test
    public void addingStringsGettingCount() {
        CountMap<String> countMap = new CountMapImpl();

        countMap.add("a");
        countMap.add("b");
        countMap.add("c");
        countMap.add("a");
        countMap.add("b");
        countMap.add("a");
        Assertions.assertEquals(3, countMap.getCount("a"));
        Assertions.assertEquals(2, countMap.getCount("b"));
        Assertions.assertEquals(1, countMap.getCount("c"));
    }
}