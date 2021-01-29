package com.example.kusoul.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.kusoul.bean.Userinfo;
import com.example.kusoul.service.IUserinfoService;

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
@RequestMapping("/userinfo")
public class UserinfoController {



    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserinfoService userinfoService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Userinfo userinfo){
        return userinfoService.add(userinfo);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return userinfoService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Userinfo userinfo){
        return userinfoService.updateData(userinfo);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Userinfo> findListByPage(@RequestParam Integer page,
                                          @RequestParam Integer pageCount){
        return userinfoService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Userinfo findById(@PathVariable Long id){
        return userinfoService.findById(id);
    }

}
