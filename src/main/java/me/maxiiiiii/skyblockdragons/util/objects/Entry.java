package me.maxiiiiii.skyblockdragons.util.objects;

import lombok.Data;

@Data
public class Entry<A, B> {
    private A a;
    private B b;

    public Entry(A a, B b) {
        this.a = a;
        this.b = b;
    }
}
