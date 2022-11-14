package me.maxiiiiii.skyblockdragons.util.objects;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
    private final List<T> queue;

    public Queue() {
        this.queue = new ArrayList<>();
    }

    /**
     * Adds t to the queue
     *
     * @param t the object to add
     * @return this instance
     */
    public Queue<T> add(T t) {
        this.queue.add(t);
        return this;
    }

    public Queue<T> remove() {
        if (this.isEmpty()) return null;

        this.queue.remove(this.size() - 1);
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

        return this.queue.get(this.size() - 1);
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
