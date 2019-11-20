package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

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
        //保存用户信息
        //1.设置激活码对应唯一用户
        user.setCode(UuidUtil.getUuid());
        //2.设置激活状态
        user.setStatus("N");
        userDao.save(user);

        //发送激活邮件
        String content="<a href='http://localhost/travel/activeUserServlet?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");

        return true;
    }

    /**
     * 激活用户
     * @param code 激活码
     * @return 激活状态
     */
    @Override
    public boolean active(String code) {
        //通过激活码查询用户
        User user = userDao.findBycode(code);
        if (user == null){
            return false;
        }
        userDao.active(user);
        return true;
    }

}
