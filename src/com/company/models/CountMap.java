package com.company.models;

import java.util.Map;

public interface CountMap<T> {

    void add(T o);

    int getCount(T o);

    int size();

    int remove(T o);

    void addAll(CountMap<? extends T> source);

    Map<T, Integer> toMap();

    void toMap(Map<? super T, Integer> destination);

}
