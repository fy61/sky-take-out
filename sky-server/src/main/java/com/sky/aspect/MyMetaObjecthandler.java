package com.sky.aspect;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自定义元数据对象处理器
 */
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();

        metaObject.setValue(AutoFillConstant.SET_CREATE_TIME, now);
        metaObject.setValue(AutoFillConstant.SET_CREATE_USER, currentId);
        metaObject.setValue(AutoFillConstant.SET_UPDATE_TIME, now);
        metaObject.setValue(AutoFillConstant.SET_UPDATE_USER, currentId);

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        //准备赋值的数据
        LocalDateTime now = LocalDateTime.now();
        Long currentId = BaseContext.getCurrentId();
        metaObject.setValue(AutoFillConstant.SET_UPDATE_TIME, now);
        metaObject.setValue(AutoFillConstant.SET_UPDATE_USER, currentId);

    }
}
