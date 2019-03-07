package cn.luoqikun.vue_boot.utils;

import lombok.SneakyThrows;

import java.security.MessageDigest;

/**
 * @Author: lqk
 * @Date: 2019/1/10 9:13
 * @Version: 1.0
 * MD5加密工具类
 */
public class MD5 {

    @SneakyThrows
    public static String getStrMD5(String str) {
        //获取MD5实例
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        //将加密字符串转换为字符数组
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[str.toCharArray().length];

        //开始加密
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] digest = md5.digest(byteArray);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            int var = digest[i] & 0xff;
            if (var < 16)
                sb.append("0");
            sb.append(Integer.toHexString(var));
        }
        return sb.toString();
    }

}
