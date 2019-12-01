package cn.itcast.travel.service;

/**
 * @outhor li
 * @create 2019-12-01 14:01
 * 收藏业务
 */
public interface FavoriteService {
    public boolean isFavorite(String rid, int uid);
    public void add(String rid, int uid);
}
