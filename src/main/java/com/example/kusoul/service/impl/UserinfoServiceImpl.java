package com.example.kusoul.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.bean.Userinfo;
import com.example.kusoul.mapper.UserinfoMapper;
import com.example.kusoul.service.IUserinfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {

    @Override
    public IPage<Userinfo> findListByPage(Integer page, Integer pageCount){
        IPage<Userinfo> wherePage = new Page<>(page, pageCount);
        Userinfo where = new Userinfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Userinfo userinfo){
        return baseMapper.insert(userinfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Userinfo userinfo){
        return baseMapper.updateById(userinfo);
    }

    @Override
    public Userinfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
