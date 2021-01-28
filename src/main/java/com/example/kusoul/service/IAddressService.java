package com.example.kusoul.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.kusoul.domain.Address;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
public interface IAddressService extends IService<Address> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Address>
     */
    IPage<Address> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param address 
     * @return int
     */
    int add(Address address);

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
     * @param address 
     * @return int
     */
    int updateData(Address address);

    /**
     * id查询数据
     *
     * @param id id
     * @return Address
     */
    Address findById(Long id);
}
