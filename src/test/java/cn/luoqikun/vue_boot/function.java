package cn.luoqikun.vue_boot;

/**
 * @Author: lqk
 * @Date: 2019/2/21 9:31
 * @Version: 1.0
 * h函数接口
 */
@FunctionalInterface
public interface function {

    public abstract void add();

    //jdk8的默认方法  子类可以不用覆写，默认为继承
    default String v() {
        return "你好，儿子";
    }

    ;
}
