package com.example.kusoul.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kusoul.domain.Good;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
public interface IGoodService extends IService<Good> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Good>
     */
    IPage<Good> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param good 
     * @return int
     */
    int add(Good good);

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
     * @param good 
     * @return int
     */
    int updateData(Good good);

    /**
     * id查询数据
     *
     * @param id id
     * @return Good
     */
    Good findById(Long id);
}
