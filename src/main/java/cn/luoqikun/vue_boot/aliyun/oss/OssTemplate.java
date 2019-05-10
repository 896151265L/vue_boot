package cn.luoqikun.vue_boot.aliyun.oss;

import cn.luoqikun.vue_boot.config.oss.OssConfig;
import com.aliyun.oss.OSSClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Author: lqk
 * @Date: 2019/1/21 23:33
 * @Version: 1.0
 */
@Component
public class OssTemplate {

    @Autowired
    private OssConfig ossConfig;


    /**
     * @param fileName
     * @param inputStream 文件上传成功后会返回文件存储地址
     */
    public String fileUpload(String fileName, InputStream inputStream) {

        OSSClient ossClient = new OSSClient(ossConfig.getEndPoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        ossClient.putObject(ossConfig.getBucketName(), fileName, inputStream);
        //上传后获取文件存储地址
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24);//设置URL过期时间
        URL ossFileURL = ossClient.generatePresignedUrl(ossConfig.getBucketName(), fileName, expiration);
        ossClient.shutdown();    //关闭oss
        return ossFileURL.toString();

    }

}
