package cn.luoqikun.vue_boot.aliyun.oss;

import com.aliyun.oss.OSSClient;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Author: lqk
 * @Date: 2019/1/21 23:33
 * @Version: 1.0
 */
public class OssTemplate {

    private static final String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    private static final String accessKeyId = "LTAI0kcx5AS0xNeU";
    private static final String accessKeySecret = "xRKVicolIg8TVVJTryFZJO3SMOuIMc";
    private static final String bucketName = "luoqi-img";

    /**
     * @param fileName
     * @param inputStream 文件上传成功后会返回文件存储地址
     */
    public static String fileUpload(String fileName, InputStream inputStream) {

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, inputStream);
        //上传后获取文件存储地址
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24);//设置URL过期时间
        URL ossFileURL = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
        ossClient.shutdown();    //关闭oss
        return ossFileURL.toString();

    }

}
