package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

/**
 * @outhor li
 * @create 2019-11-19 1:59
 */
public interface UserService {
    /**
     * 注册用户
     * @param user 注册用户信息
     * @return 是否注册成功
     */
    public boolean regist(User user);

    /**
     * 激活用户
     * @param code 激活码
     * @return 激活状态
     */
    boolean active(String code);
}
