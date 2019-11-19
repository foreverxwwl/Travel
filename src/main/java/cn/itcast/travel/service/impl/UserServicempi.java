package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;

/**
 * @outhor li
 * @create 2019-11-19 1:58
 */
public class UserServicempi implements UserService {
    private UserDao userDao = new UserDaoImpl();
    /**
     * 注册用户
     * @param user 注册用户信息
     * @return 是否注册成功
     */
    @Override
    public boolean regist(User user) {
        //通过用户名查询用户
        User byUsername = userDao.findByUsername(user.getUsername());
        //如果用户已存在
        if (byUsername != null){
            //返回失败
            return false;
        }
        //保存用户
        userDao.save(user);
        return false;
    }

}
