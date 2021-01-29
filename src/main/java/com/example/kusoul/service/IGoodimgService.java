package com.example.kusoul.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kusoul.bean.Goodimg;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
public interface IGoodimgService extends IService<Goodimg> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Goodimg>
     */
    IPage<Goodimg> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param goodimg 
     * @return int
     */
    int add(Goodimg goodimg);

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
     * @param goodimg 
     * @return int
     */
    int updateData(Goodimg goodimg);

    /**
     * id查询数据
     *
     * @param id id
     * @return Goodimg
     */
    Goodimg findById(Long id);
}
