package com.company.models;

import java.util.*;

public class CountMapImpl<T> implements CountMap<T> {

    public class Node<T> {

        private T key;
        private Integer value;

        public Node(T key) {
            this.key = key;
            this.value = 1;
        }

        public Node(T key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return this.key;
        }

        public void setKey(T key) {
            this.key = key;
        }

        public Integer getValue() {
            return this.value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public void updateValue() {
            this.value++;
        }

        public void updateValue(Integer value) {
            this.value += value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?> node = (Node<?>) o;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }
    }

    private Set<Node<T>> set;

    public CountMapImpl() {
        this.set = new HashSet<>();
    }

    @Override
    public void add(T t) {

        Node<T> inputNode = new Node<>(t, 1);

        if (set.contains(inputNode)) {
            for (Node<T> node : set) {
                if (node.equals(inputNode)) {
                    node.updateValue();
                    break;
                }
            }
        } else {
            set.add(new Node<T>(t));
        }
    }

    public void add(T t, Integer value) {

        Node<T> inputNode = new Node<>(t, value);

        if (set.contains(inputNode)) {
            for (Node<T> node : set) {
                if (node.equals(inputNode)) {
                    node.updateValue(value);
                    break;
                }
            }
        } else {
            set.add(new Node<T>(t, value));
        }
    }

    @Override
    public int getCount(T t) {
        for (Node<T> node : set) {
            if (node.getKey().equals(t)) {
                return node.getValue();
            }
        }
        return 0;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public int remove(T t) {
        Iterator<Node<T>> it = set.iterator();

        while (it.hasNext()) {
            Node<T> node = it.next();
            if (node.getKey().equals(t)) {
                it.remove();
                return node.getValue();
            }
        }
        return 0;
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        Map<T, Integer> map = (Map<T, Integer>) source.toMap();

        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            add(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        Map<T, Integer> map = new TreeMap<>();
        for (Node<T> node : set) {
            map.put(node.getKey(), node.getValue());
        }
        return map;
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        for (Node<T> node : set) {
            destination.put(node.getKey(), node.getValue());
        }
    }
}
