package cn.luoqikun.vue_boot;

import cn.luoqikun.vue_boot.entity.User;

/**
 * @Author: lqk
 * @Date: 2019/2/20 9:09
 * @Version: 1.0
 */
public class Prodoct {

    class Dog {
        //返回对外部类的引用
        Prodoct getProdoct() {
            return Prodoct.this;
        }
    }

    //返回内部类的引用
    Dog getDog() {
        //注意： 要创建内部类对象必须先创建外部类对象
        Prodoct.Dog dog = new Prodoct().new Dog();//创建内部类的对象
        return dog;
    }

    //匿名内部类
    User getUser() {
        return new User() {
            String name = "罗启坤";
            Integer age = 28;
        };
    }

    /**
     * 等同于下边class的缩写
     * 类cont 隐式的
     */
    class cont extends User {
        String name = "罗启坤";
        Integer age = 28;

    }
}
