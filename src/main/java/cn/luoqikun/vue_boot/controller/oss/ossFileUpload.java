package cn.luoqikun.vue_boot.controller.oss;

import cn.luoqikun.vue_boot.aliyun.oss.OssTemplate;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @Author: lqk
 * @Date: 2019/2/18 10:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("oss")
public class ossFileUpload {

    @Autowired
    private OssTemplate ossTemplate;

    //上传文件到OSS
    @PostMapping("/fileUpload")
    @SneakyThrows
    public void fileUpload(@RequestBody MultipartFile file) {

        InputStream inputStream = file.getInputStream();
        String filename = file.getOriginalFilename();
        String fileURL = ossTemplate.fileUpload(filename, inputStream);
        //TODO...   fileURL为oss文件地址 保存到数据库
    }

}
