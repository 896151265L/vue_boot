package cn.luoqikun.vue_boot;

/**
 * @Author: lqk
 * @Date: 2019/3/7 19:05  回调函数
 * @Version: 1.0
 */
public class TestCallback {

    public void test(int i ,CallBack allBack){
        for (int j1 =0 ; j1 <i; j1++){
            String s = allBack.onComputeEnd();
            System.out.println("第"+ (j1+1) +"调用成功了" + s );
        }

    }
}
