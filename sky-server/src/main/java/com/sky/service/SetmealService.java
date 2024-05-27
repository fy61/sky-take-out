package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐
     * @param setmeal
     */
    void saveOne(Setmeal setmeal);

    /**
     * 分页查询
     * @param setmealPageQueryDTO
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
