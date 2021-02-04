package com.example.kusoul.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.bean.Role;
import com.example.kusoul.bean.User;
import com.example.kusoul.mapper.RoleMapper;
import com.example.kusoul.mapper.UserMapper;
import com.example.kusoul.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Resource
    RoleMapper roleMapper;
    @Override
    public IPage<User> findListByPage(Integer page, Integer pageCount){
        IPage<User> wherePage = new Page<>(page, pageCount);
        User where = new User();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(User User){
        return baseMapper.insert(User);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(User User){
        return baseMapper.updateById(User);
    }

    @Override
    public User findById(Long id){
        return  baseMapper.selectById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);

        User user =  baseMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("id", user.getId());

        List<Role> roless = roleMapper.selectList(queryWrapper1);
        user.setRoles(roless);
        return user;
    }
}
