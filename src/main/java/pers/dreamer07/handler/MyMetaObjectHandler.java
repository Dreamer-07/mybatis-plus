package pers.dreamer07.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自定义处理器，实现 MetaObjectHandler 接口配置填充策略
 * @program: mybatis-plus
 * @description:
 * @author: EMTKnight
 * @create: 2021-03-11
 **/
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时的填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert fill start...");
        /*
        * 方式一：
        *   setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)：通用填充
        *       fieldName：属性名
        *       fieldVal：属性值
        *       metaObject：元数据
        * */
//        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("updateTime", new Date(), metaObject);

        /*
        * 方式二：使用 3.3.0 中添加的 {@link #strictInsertFill} or {@link #strictUpdateFill}
        *   strictInsertFill(metaObject, fieldName, fieldType, fieldVal) - strictUpdateFill() 同理
        *       想根据注解 FieldFill.xxx(insert / update / insert_update) 和 字段名(fieldName) 以及 字段类型(fieldType) 来区分必须使用 父类的strictInsertFill或者strictUpdateFill方法
        * */
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

    /**
     * 修改时的填充策略
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update fill start...");
//        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }

}
