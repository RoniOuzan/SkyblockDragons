package me.maxiiiiii.skyblockdragons.util.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapQueue<T> {
    private List<Entry<Object, T>> queue;

    public MapQueue() {
        this.queue = new ArrayList<>();
    }

    /**
     * Adds t to the queue
     *
     * @param t the object to add
     * @param key the key to put the t to
     * @return this instance
     */
    public MapQueue<T> add(Object key, T t) {
        this.queue = this.queue.stream().filter(e -> !e.getA().equals(key)).collect(Collectors.toList());

        this.queue.add(new Entry<>(key, t));
        return this;
    }

    public MapQueue<T> remove() {
        if (this.isEmpty()) return null;

        this.queue.remove(this.queue.size() - 1);
        return this;
    }

    /**
     * @return the last object in the queue, if the queue is empty it will return null
     */
    public T get() {
        return getOrDefault(null);
    }

    /**
     * @return the last object in the queue, if the queue is empty it will return defaultValue
     */
    public T getOrDefault(T defaultValue) {
        if (this.isEmpty()) return defaultValue;

        return this.queue.get(this.queue.size() - 1).getB();
    }

    /**
     * Pulls the last object from the queue and removes it
     * @return the last object in the queue, if the queue is empty it will return null
     */
    public T pull() {
        if (this.isEmpty()) return null;

        T t = this.get();
        this.queue.remove(this.queue.size() - 1);
        return t;
    }

    /**
     * @return the size of the queue
     */
    public int size() {
        return this.queue.size();
    }

    /**
     * @return if the queue is empty
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String toString() {
        return this.queue.toString();
    }
}
