package cn.luoqikun.vue_boot.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Pages implements Serializable {

    private static final long serialVersionUID = 3817394928646122310L;

    /**
     * 当前页
     */
    private int pageNum = 1;
    /**
     * 每页的数量
     */
    private int pageSize = 5;
}
