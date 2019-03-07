package cn.luoqikun.vue_boot.service;

import cn.luoqikun.vue_boot.entity.BaseEntity;

import java.util.List;


public interface BaseService<T extends BaseEntity> {

    void save(T t);             //新增

    void delete(Long id);       //删除

    void update(T t);           //修改

    List<T> getAll();           //查询
}
