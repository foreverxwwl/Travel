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

    /**
     * 通过激活码查找用户
     * @param code 激活码
     * @return 找到的用户
     */
    User findBycode(String code);

    /**
     * 修改用户激活状态
     * @param user 待修改用户
     */
    void active(User user);
}
