package com.sky.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Dish;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import com.sky.service.SetmealDishService;
import com.sky.service.SetmealService;
import com.sky.service.impl.SetmealServiceImpl;
import com.sky.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增套餐
     *
     * @param setmealDTO
     * @return
     */
    @PostMapping()
    @ApiOperation("新增套餐")
    @Transactional
    public Result save(@RequestBody SetmealDTO setmealDTO) {
        log.info("新增套餐:{}", setmealDTO);

        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealService.saveOne(setmeal);

        Long id = setmeal.getId();
        List<SetmealDish> stealDishes = setmealDTO.getStealDishes();

        if (stealDishes != null && stealDishes.size() > 0) {
            stealDishes.stream().map((setmealDish) -> {
                setmealDish.setDishId(id);
                return setmealDish;
            }).collect(Collectors.toList());

            setmealDishService.saveBatch(stealDishes);
        }

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        log.info("套餐分页查询 参数为{}", setmealPageQueryDTO);
/*
        List<SetmealVO> setmealVOS = new ArrayList<>();//准备好的集合字段
        List<Setmeal> setmeals = setmealService.list();
        for (Setmeal setmeal: setmeals) {
            Category category = categoryService.getById(setmeal.getCategoryId());
        }*/
        PageResult pageResult = setmealService.pageQuery(setmealPageQueryDTO);
        return Result.success();
    }
}
