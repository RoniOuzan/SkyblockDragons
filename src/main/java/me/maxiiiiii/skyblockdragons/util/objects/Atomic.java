package me.maxiiiiii.skyblockdragons.util.objects;

public class Atomic<T> {
    private T value;

    public Atomic(T value) {
        this.value = value;
    }

    public T set(T value) {
        this.value = value;
        return this.value;
    }

    public T get() {
        return this.value;
    }
}
