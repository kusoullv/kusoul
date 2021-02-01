package com.example.kusoul.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.bean.UserRole;
import com.example.kusoul.mapper.UserRoleMapper;
import com.example.kusoul.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-02-01
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Override
    public  IPage<UserRole> findListByPage(Integer page, Integer pageCount){
        IPage<UserRole> wherePage = new Page<>(page, pageCount);
        UserRole where = new UserRole();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(UserRole userRole){
        return baseMapper.insert(userRole);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(UserRole userRole){
        return baseMapper.updateById(userRole);
    }

    @Override
    public UserRole findById(Long id){
        return  baseMapper.selectById(id);
    }
}
