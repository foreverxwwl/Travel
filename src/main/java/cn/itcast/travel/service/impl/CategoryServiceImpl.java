package cn.itcast.travel.service.impl;
import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @outhor li
 * @create 2019-11-26 0:00
 */
public class CategoryServiceImpl implements CategoryService {
    CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 查询所有,使用Redis缓存优化
     * @return
     */
    public List<Category> findAll() {

        //1.查询Redis数据库
        //获取jedis数据库
        Jedis jedis = JedisUtil.getJedis();
        //可使用sortedset排序查询
        //Set<String> categorys = jedis.zrange("category", 0, -1);
        //1.3查询sortedset中的分数(cid)和值(cname)
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);
        List<Category> cs = null;
        if (categorys.size() == 0 || categorys == null){
            //2.如果没有查到，那么查询数据库
            cs = categoryDao.findAll();
            //将查询结果保存
            for (int i = 0; i < cs.size(); i++) {
                jedis.zadd("category", cs.get(i).getCid(), cs.get(i).getCname());
            }
        }else {
            //3.如果查到了，那么将查到的Set集合categorys存入List集合cs
            cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                //封装一个分类集合
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                //将集合放入cs中
                cs.add(category);
            }
        }
        return cs;
    }
}
