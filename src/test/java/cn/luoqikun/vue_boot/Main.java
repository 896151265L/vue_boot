package cn.luoqikun.vue_boot;

/**
 * @Author: lqk
 * @Date: 2019/3/7 19:07
 * @Version: 1.0
 */
public class Main {

    public static void main(String[] args){

        new TestCallback().test(10, new CallBack() {
            @Override
            public String onComputeEnd() {

                return "嘿嘿";
            }
        });
    }
}
