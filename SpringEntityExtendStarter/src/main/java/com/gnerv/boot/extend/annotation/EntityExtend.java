package com.gnerv.boot.extend.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 实体对象扩展类注解
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/28
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface EntityExtend {

    /**
     * 扩展给哪个类 例：UserDTO.class
     *
     * @return 扩展给哪个类
     */
    Class[] masterEntity();

    /**
     * 查询方法全名 例：com.gnerv.boot.platform.mapper.OrgMapper.selectByUserId
     *
     * @return 方法全名
     */
    String selectMethod();

    /**
     * 入参 形似同xml文件的关联查询 参数名称=主表字段名称 例：userId=b_id
     *
     * @return 入参
     */
    String column();

    /**
     * 返回参数类型 Object/List  例：Role.class / List.class
     *
     * @return 返回参数类型
     */

    Class resultType();

    /**
     * 扩展map的key名称
     *
     * @return 扩展map的key名称
     */
    String mapKey();
}
