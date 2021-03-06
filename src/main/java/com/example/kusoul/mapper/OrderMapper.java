package com.example.kusoul.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kusoul.bean.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
