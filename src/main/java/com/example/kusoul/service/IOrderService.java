package com.example.kusoul.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kusoul.bean.Order;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
public interface IOrderService extends IService<Order> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Order>
     */
    IPage<Order> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param order 
     * @return int
     */
    int add(Order order);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param order 
     * @return int
     */
    int updateData(Order order);

    /**
     * id查询数据
     *
     * @param id id
     * @return Order
     */
    Order findById(Long id);
}
