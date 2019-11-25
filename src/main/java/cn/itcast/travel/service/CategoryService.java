package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @outhor li
 * @create 2019-11-25 23:56
 * 分类条目 service
 */
public interface CategoryService {
    /**
     * 查询所有
     * @return
     */
    public List<Category> findAll();
}
