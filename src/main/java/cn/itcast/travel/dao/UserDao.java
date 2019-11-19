package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @outhor li
 * @create 2019-11-19 14:53
 */
public interface UserDao {
    /**
     * 按照用户名查找
     * @param username 待查找的用户名
     * @return 查找到的用户
     */
    public User findByUsername(String username);

    /**
     * 保存用户
     * @param user 待保存的用户
     */
    public void save(User user);
}
