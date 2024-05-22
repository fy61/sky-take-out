package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

public interface CategoryService extends IService<Category> {
    /**
     * 分类管理分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 启用、禁用分类
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 新增菜品分类
     * @param category
     */
    void insert(Category category);

    /**
     * 修改分类
     * @param category
     */
    void updateByIdOne(Category category);
}
