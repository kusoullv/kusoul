package com.example.kusoul.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.domain.Order;
import com.example.kusoul.mapper.OrderMapper;
import com.example.kusoul.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Override
    public IPage<Order> findListByPage(Integer page, Integer pageCount){
        IPage<Order> wherePage = new Page<>(page, pageCount);
        Order where = new Order();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Order order){
        return baseMapper.insert(order);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Order order){
        return baseMapper.updateById(order);
    }

    @Override
    public Order findById(Long id){
        return  baseMapper.selectById(id);
    }
}
