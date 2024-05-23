package com.sky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.entity.Dish;
import com.sky.entity.DishFlavor;

import java.util.List;

public interface DishService extends IService<Dish> {


    /**新增菜品
     * @param dish
     */
    void saveOne(Dish dish);
}
