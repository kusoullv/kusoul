package com.example.kusoul.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kusoul.bean.UserRole;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2021-02-01
 */
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<UserRole>
     */
    IPage<UserRole> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param userRole 
     * @return int
     */
    int add(UserRole userRole);

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
     * @param userRole 
     * @return int
     */
    int updateData(UserRole userRole);

    /**
     * id查询数据
     *
     * @param id id
     * @return UserRole
     */
    UserRole findById(Long id);
}
