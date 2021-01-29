package com.example.kusoul.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.bean.Goodimg;
import com.example.kusoul.mapper.GoodimgMapper;
import com.example.kusoul.service.IGoodimgService;
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
public class GoodimgServiceImpl extends ServiceImpl<GoodimgMapper, Goodimg> implements IGoodimgService {

    @Override
    public IPage<Goodimg> findListByPage(Integer page, Integer pageCount){
        IPage<Goodimg> wherePage = new Page<>(page, pageCount);
        Goodimg where = new Goodimg();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Goodimg goodimg){
        return baseMapper.insert(goodimg);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Goodimg goodimg){
        return baseMapper.updateById(goodimg);
    }

    @Override
    public Goodimg findById(Long id){
        return  baseMapper.selectById(id);
    }
}
