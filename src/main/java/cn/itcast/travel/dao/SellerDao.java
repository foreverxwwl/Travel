package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @outhor li
 * @create 2019-12-01 11:20
 * 查询商家信息
 */
public interface SellerDao {
    public Seller findByid(int sid);
}
