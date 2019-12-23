package com.gnerv.boot.extend.store;

import com.gnerv.boot.extend.annotation.EntityExtend;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 存储包含实体扩展类注解的类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/9/4
 */
public class EntityExtendBean {

    public static Map<Class, Set<EntityExtend>> BEANS_MAP = new HashMap<>();

}
