package com.sky.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 分类管理分页查询
     *
     * @param categoryPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        Page<Category> pageInfo = new Page<>(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(categoryPageQueryDTO.getName() != null, Category::getName, categoryPageQueryDTO.getName());
        queryWrapper.like(categoryPageQueryDTO.getType() != null, Category::getType, categoryPageQueryDTO.getType());
        categoryMapper.selectPage(pageInfo, queryWrapper);
        long total = pageInfo.getTotal();
        List<Category> records = pageInfo.getRecords();
        return new PageResult(total, records);

    }

    /**
     * 启用、禁用分类
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Category category = Category.builder()
                .status(status)
                .id(id)
                .build();
        categoryMapper.updateById(category);
    }

    /**
     * 新增菜品分类
     * @param category
     */
    @AutoFill(value = OperationType.INSERT)
    @Override
    public void insert(Category category) {
        categoryMapper.insert(category);
    }

    /**
     * 修改分类
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    @Override
    public void updateByIdOne(Category category) {
        categoryMapper.updateById(category);
    }
}