package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @outhor li
 * @create 2019-12-01 11:10
 * 查询页面详细图片
 */
public interface RouteImgDao {
    public List<RouteImg> findByRid(int rid);
}
