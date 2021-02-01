package com.example.kusoul.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.kusoul.bean.UserRole;
import com.example.kusoul.service.IUserRoleService;
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
 * @since 2021-02-01
 */
@Api(tags = {""})
@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserRoleService userRoleService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody UserRole userRole){
        return userRoleService.add(userRole);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return userRoleService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody UserRole userRole){
        return userRoleService.updateData(userRole);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<UserRole> findListByPage(@RequestParam Integer page,
                                   @RequestParam Integer pageCount){
        return userRoleService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public UserRole findById(@PathVariable Long id){
        return userRoleService.findById(id);
    }

}
