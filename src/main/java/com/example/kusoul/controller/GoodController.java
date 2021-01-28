package com.example.kusoul.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.kusoul.domain.Good;
import com.example.kusoul.service.IGoodService;
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
@RequestMapping("/good")
public class GoodController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IGoodService goodService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Good good){
        return goodService.add(good);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return goodService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Good good){
        return goodService.updateData(good);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Good> findListByPage(@RequestParam Integer page,
                                      @RequestParam Integer pageCount){
        return goodService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Good findById(@PathVariable Long id){
        return goodService.findById(id);
    }

}
