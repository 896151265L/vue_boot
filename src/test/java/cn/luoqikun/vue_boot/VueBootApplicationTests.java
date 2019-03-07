package cn.luoqikun.vue_boot;

import cn.luoqikun.vue_boot.controller.UserController;
import cn.luoqikun.vue_boot.controller.alipay.Pc_aliPayController;
import cn.luoqikun.vue_boot.entity.User;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VueBootApplicationTests {

    @Autowired
    UserController userController;

    @Autowired
    public Pc_aliPayController pc_aliPayController;

    public static final String REG = "(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE = "yyyy-MM-dd";
    public static final String TIME = "HH:mm:ss";


    @Test
    @SneakyThrows
    public void contextLoads() {

        User user = new User();
        user.setAccount("ancda");
        user.setPassWord("123");


        System.out.println(JSON.toJSONString(user));


    }
}

