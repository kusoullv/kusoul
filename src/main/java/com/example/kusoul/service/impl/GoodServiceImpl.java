package com.example.kusoul.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.bean.Good;
import com.example.kusoul.mapper.GoodMapper;
import com.example.kusoul.service.IGoodService;
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
public class GoodServiceImpl extends ServiceImpl<GoodMapper, Good> implements IGoodService {

    @Override
    public IPage<Good> findListByPage(Integer page, Integer pageCount){
        IPage<Good> wherePage = new Page<>(page, pageCount);
        Good where = new Good();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Good good){
        return baseMapper.insert(good);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Good good){
        return baseMapper.updateById(good);
    }

    @Override
    public Good findById(Long id){
        return  baseMapper.selectById(id);
    }
}
