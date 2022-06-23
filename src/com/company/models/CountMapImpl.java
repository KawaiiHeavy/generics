package com.company.models;

import java.util.*;

public class CountMapImpl<T> implements CountMap<T> {

    public class Node<T> {

        private T key;
        private Integer value;

        public Node(T key){
            this.key = key;
            this.value = 1;
        }

        public Node(T key, Integer value){
            this.key = key;
            this.value = value;
        }

        public T getKey(){
            return this.key;
        }

        public void setKey(T key){
            this.key = key;
        }

        public Integer getValue(){
            return this.value;
        }

        public void setValue(Integer value){
            this.value = value;
        }

        public void updateValue(){
            this.value++;
        }

        public void updateValue(Integer value){
            this.value += value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    private Collection<Node<T>> collection;

    public CountMapImpl(){
        this.collection = new ArrayList<>();
    }

    @Override
    public void add(T t) {
        boolean exists = false;
        for (Node<T> node: collection){
            if (node.getKey().equals(t)){
                exists = true;
                node.updateValue();
                break;
            }
        }
        if (!exists) {
            collection.add(new Node<T>(t));
        }
    }

    @Override
    public int getCount(T t) {
        for (Node<T> node: collection){
            if (node.getKey().equals(t)){
                return node.getValue();
            }
        }
        return 0;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public int remove(T t) {
        for (Node<T> node: collection){
            if (node.getKey().equals(t)){
                collection.remove(node);
                return node.getValue();
            }
        }
        return 0;
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        Map<T, Integer> map = (Map<T, Integer>) source.toMap();
        boolean isExist;

        for (Map.Entry<T, Integer> entry : map.entrySet()){
            isExist = false;
            for (Node<T> node : collection){
                if (node.getKey().equals(entry.getKey())){
                    isExist = true;
                    node.updateValue(entry.getValue());
                }
            }
            if (!isExist){
                collection.add(new Node<T>(entry.getKey(), entry.getValue()));
            }
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        Map<T, Integer> map = new TreeMap<>();
        for (Node<T> node: collection){
            map.put(node.getKey(), node.getValue());
        }
        return map;
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        for (Node<T> node : collection){
            destination.put(node.getKey(), node.getValue());
        }
    }
}
