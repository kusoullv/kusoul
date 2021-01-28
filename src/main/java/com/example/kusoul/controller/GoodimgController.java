package com.example.kusoul.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.kusoul.domain.Goodimg;
import com.example.kusoul.service.IGoodimgService;
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
@RequestMapping("/goodimg")
public class GoodimgController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private IGoodimgService goodimgService;


    @ApiOperation(value = "新增")
    @PostMapping()
    public int add(@RequestBody Goodimg goodimg){
        return goodimgService.add(goodimg);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("{id}")
    public int delete(@PathVariable("id") Long id){
        return goodimgService.delete(id);
    }

    @ApiOperation(value = "更新")
    @PutMapping()
    public int update(@RequestBody Goodimg goodimg){
        return goodimgService.updateData(goodimg);
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping()
    public IPage<Goodimg> findListByPage(@RequestParam Integer page,
                                         @RequestParam Integer pageCount){
        return goodimgService.findListByPage(page, pageCount);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("{id}")
    public Goodimg findById(@PathVariable Long id){
        return goodimgService.findById(id);
    }

}
