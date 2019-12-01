package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @outhor li
 * @create 2019-12-01 14:06
 */
public interface FavoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);
    public int findCountByRid(int rid);
    public void add(int rid, int uid);
}
