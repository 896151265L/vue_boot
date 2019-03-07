package cn.luoqikun.vue_boot;


import cn.luoqikun.vue_boot.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @Author: lqk
 * @Date: 2019/2/21 11:02
 * @Version: 1.0
 * jdk 8 流学习  stream：操作集合对象
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Stream_Study {

    @Test
    public void Test() {

        List<Integer> coll = Stream.of(1, 2, 3, 4, 6, 8, 9, 10, 5, 7).collect(toList());
        Collections.sort(coll);
        Collections.sort(coll, (s1, s2) -> s2.compareTo(s1));
        coll.forEach(e -> System.out.println(e));

        //数组流对象
        String[] str = {"我", "爱", "学", "习", ""};
        Stream<String> streamStr = Arrays.stream(str);
        //集合流对象
        List<String> list = Arrays.asList(str);
        Stream<String> streamList = list.stream();

        //把值转换为流对象
        Stream<Integer> integerStream = Stream.of(9, 12, 45, 1, 2, 3, 3, 4, 4, 5, 6, 7);

        List<Integer> collect = integerStream.limit(12)
                .sorted(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);//排序(倒序)，默认升序
                    }
                })
                .distinct()
                .filter(e -> e != 100)
                .skip(2)  //过滤前两个
                .collect(toList());
        collect.forEach(e -> System.out.println(e));


        List<User> userList = new ArrayList<User>();
        User user1 = new User();
        user1.setUserName("洛奇1");
        user1.setAccount("1");
        userList.add(user1);
        User user3 = new User();
        user3.setUserName("洛奇3");
        user3.setAccount("3");
        userList.add(user3);
        User user2 = new User();
        user2.setUserName("洛奇2");
        user2.setAccount("2");
        userList.add(user2);

        List<User> users = userList.stream()
                .sorted(Comparator.comparing(User::getAccount).reversed()) //默认升序，(reversed()方法为降序)
                .collect(toList());
        users.forEach(e -> System.out.println(e));


    }
}
