package cn.luoqikun.vue_boot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {

    //父类实现序列化后子类可以不用实现序列化
    private static final long serialVersionUID = -6329981151444434527L;

    //数据库主键
    @TableId
    private Long id;
    //创建时间
    private LocalDateTime crateTime = LocalDateTime.now();

}
