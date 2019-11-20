package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @outhor li
 * @create 2019-11-19 14:53
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 按照用户名查找
     * @param username 待查找的用户名
     * @return 查找到的用户
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        try {//应为查询不到会抛出异常，所以提前捕捉
            //设置sql
            String sql = "select * from tab_user where username = ?";
            //执行sql
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        }catch (Exception e){

        }
        return user;
    }

    /**
     * 保存用户
     * @param user 待保存的用户
     */
    @Override
    public void save(User user) {
        //1.定义sql
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        template.update(sql,user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    /**
     * 通过激活码查找用户
     * @param code 激活码
     * @return 找到的用户
     */
    @Override
    public User findBycode(String code) {
        User user = null;
        try {
            String sql = "select * from tab_user where code = ?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;

    }

    /**
     * 修改用户激活状态
     * @param user 待修改用户
     */
    @Override
    public void active(User user) {
        String sql = "update tab_user set status = 'Y' where code=?";
        template.update(sql,user.getCode());
    }
}
