package com.example.kusoul.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.kusoul.domain.Address;
import com.example.kusoul.mapper.AddressMapper;
import com.example.kusoul.service.IAddressService;
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
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Override
    public IPage<Address> findListByPage(Integer page, Integer pageCount){
        IPage<Address> wherePage = new Page<>(page, pageCount);
        Address where = new Address();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(Address address){
        return baseMapper.insert(address);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(Address address){
        return baseMapper.updateById(address);
    }

    @Override
    public Address findById(Long id){
        return  baseMapper.selectById(id);
    }
}
