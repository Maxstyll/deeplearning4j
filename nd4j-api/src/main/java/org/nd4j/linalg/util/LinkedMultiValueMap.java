package org.nd4j.linalg.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class LinkedMultiValueMap<K, V> implements MultiValueMap<K, V>, Serializable {
    private static final long serialVersionUID = 3801124242820219131L;
    private final Map<K, List<V>> targetMap;

    public LinkedMultiValueMap() {
        this.targetMap = new LinkedHashMap();
    }

    public LinkedMultiValueMap(int initialCapacity) {
        this.targetMap = new LinkedHashMap(initialCapacity);
    }

    public LinkedMultiValueMap(Map<K, List<V>> otherMap) {
        this.targetMap = new LinkedHashMap(otherMap);
    }

    public void add(K key, V value) {
        List<V> values = this.targetMap.get(key);
        if(values == null) {
            values = new LinkedList<>();
            this.targetMap.put(key, values);
        }

        ((List)values).add(value);
    }

    public V getFirst(K key) {
        List<V> values = (List) this.targetMap.get(key);
        return values != null?values.get(0):null;
    }

    public void set(K key, V value) {
        LinkedList values = new LinkedList();
        values.add(value);
        this.targetMap.put(key, values);
    }

    public void setAll(Map<K, V> values) {
        Iterator i$ = values.entrySet().iterator();

        while(i$.hasNext()) {
            Entry<K,V> entry = (Entry)i$.next();
            this.set(entry.getKey(), entry.getValue());
        }

    }

    public Map<K, V> toSingleValueMap() {
        LinkedHashMap singleValueMap = new LinkedHashMap(this.targetMap.size());
        Iterator i$ = this.targetMap.entrySet().iterator();

        while(i$.hasNext()) {
            Entry entry = (Entry)i$.next();
            singleValueMap.put(entry.getKey(), ((List)entry.getValue()).get(0));
        }

        return singleValueMap;
    }

    public int size() {
        return this.targetMap.size();
    }

    public boolean isEmpty() {
        return this.targetMap.isEmpty();
    }

    public boolean containsKey(Object key) {
        return this.targetMap.containsKey(key);
    }

    public boolean containsValue(Object value) {
        return this.targetMap.containsValue(value);
    }

    public List<V> get(Object key) {
        return (List)this.targetMap.get(key);
    }

    public List<V> put(K key, List<V> value) {
        return (List)this.targetMap.put(key, value);
    }

    public List<V> remove(Object key) {
        return (List)this.targetMap.remove(key);
    }

    public void putAll(Map<? extends K, ? extends List<V>> m) {
        this.targetMap.putAll(m);
    }

    public void clear() {
        this.targetMap.clear();
    }

    public Set<K> keySet() {
        return this.targetMap.keySet();
    }

    public Collection<List<V>> values() {
        return this.targetMap.values();
    }

    public Set<Entry<K, List<V>>> entrySet() {
        return this.targetMap.entrySet();
    }

    public boolean equals(Object obj) {
        return this.targetMap.equals(obj);
    }

    public int hashCode() {
        return this.targetMap.hashCode();
    }

    public String toString() {
        return this.targetMap.toString();
    }
}
