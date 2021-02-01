package com.example.kusoul.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kusoul.bean.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2021-02-01
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Role>
     */
    IPage<Role> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param role 
     * @return int
     */
    int add(Role role);

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
     * @param role 
     * @return int
     */
    int updateData(Role role);

    /**
     * id查询数据
     *
     * @param id id
     * @return Role
     */
    Role findById(Long id);
}
