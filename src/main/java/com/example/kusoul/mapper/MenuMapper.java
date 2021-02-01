package com.example.kusoul.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.kusoul.bean.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maqh
 * @since 2021-02-01
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getAllMenus();

}
