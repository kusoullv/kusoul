package com.example.kusoul.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kusoul.bean.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maqh
 * @since 2021-02-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
