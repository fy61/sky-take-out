package com.sky.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.sky.annotation.AutoFill;

import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * 分类管理
 */
@RestController
@RequestMapping("/admin/category")
@Slf4j
@Api(tags = "分类管理相关接口")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类管理分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分类管理分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        log.info("分类管理分页查询 参数为{}", categoryPageQueryDTO);
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    @ApiOperation("新增菜品分类")
    public Result save(@RequestBody Category category) {
        log.info("新增菜品参数为:{}", category);
        category.setStatus(1);
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
//        category.setCreateUser(BaseContext.getCurrentId());
//        category.setUpdateUser(BaseContext.getCurrentId());
        categoryService.insert(category);
        return Result.success();
    }

    /**
     * 启用、禁用分类
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("启用禁用分类")
    public Result startOrstop(@PathVariable Integer status, Long id) {
        log.info("启用禁用分类:{} {}", status, id);
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除分类管理")
    public Result<String> delete(Long id) {
        log.info("删除分类：{}", id);
        categoryService.removeById(id);
        return Result.success();
    }

    /**
     * 修改分类
     *
     * @param category
     * @return
     */
    @PutMapping
    @ApiOperation("修改分类")
    @AutoFill(value = OperationType.UPDATE)
    public Result<String> update(@RequestBody Category category) {
        log.info("修改分类参数为:{}", category);
        categoryService.updateByIdOne(category);
        return Result.success();
    }

    @GetMapping("/list")
    @ApiOperation("根据菜品查询分类")
    public Result<List<Category>> selectBytype(Integer type) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getType,type);
        List<Category> list = categoryService.list(queryWrapper);
        return Result.success(list);
    }
}
