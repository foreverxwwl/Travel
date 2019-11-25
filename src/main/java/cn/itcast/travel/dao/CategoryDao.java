package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @outhor li
 * @create 2019-11-26 0:12
 */
public interface CategoryDao {
    public List<Category> findAll();
}
