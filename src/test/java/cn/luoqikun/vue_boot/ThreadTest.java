package cn.luoqikun.vue_boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: lqk
 * @Date: 2019/3/4 16:59
 * @Version: 1.0  线程学习
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest extends Thread {


    @Test
    void thread() {

    }


    @Override
    public synchronized void start() {
        super.start();
    }

    @Override
    public void run() {
        super.run();
    }
}
