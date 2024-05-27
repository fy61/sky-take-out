package com.sky.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.yulichang.base.MPJBaseMapper;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.vo.SetmealVO;

@org.apache.ibatis.annotations.Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {
    /**
     * 分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
