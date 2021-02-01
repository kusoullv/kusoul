package com.example.kusoul.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kusoul.bean.Menu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2021-02-01
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Menu>
     */
    IPage<Menu> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param menu 
     * @return int
     */
    int add(Menu menu);

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
     * @param menu 
     * @return int
     */
    int updateData(Menu menu);

    /**
     * id查询数据
     *
     * @param id id
     * @return Menu
     */
    Menu findById(Long id);

}
