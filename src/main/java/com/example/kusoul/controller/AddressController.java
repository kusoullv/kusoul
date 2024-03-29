package com.example.kusoul.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.kusoul.bean.Address;
import com.example.kusoul.service.IAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author maqh
 * @since 2021-01-28
 */
@Api(tags = {""})
@RestController
public class AddressController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IAddressService addressService;

    @ApiOperation(value = "新增")
    @PostMapping("add")
    public int add(@RequestBody Address address){
        return addressService.add(address);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("delete{id}")
    public int delete(@PathVariable("id") Long id){
        return addressService.delete(id);
    }

    @ApiOperation(value = "更新", notes = "invokePost说明", httpMethod = "POST")
    @PutMapping("update")
    public int update(@RequestBody Address address){
        return addressService.updateData(address);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping("findListByPage")
    public IPage<Address> findListByPage(@RequestParam Integer page,
                                         @RequestParam Integer pageCount){
        return addressService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("findById{id}")
    public Address findById(@PathVariable Long id){
        return addressService.findById(id);
    }


    @ApiOperation(value = "id查询")
    @GetMapping("aaa")
    public String aaa(){
        String ss = "sdfds";
        ss = ss +"fsdfds";
        return "";
    }


}
