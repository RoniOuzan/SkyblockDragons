package me.maxiiiiii.skyblockdragons.util.reflection;


/**
 * Created by Jan on 18-02-2017.
 */
public interface IReflectionObject {
    <E extends IReflectionObject> E setAccessible(boolean value);

    ReflectionUtil newCall();
}
