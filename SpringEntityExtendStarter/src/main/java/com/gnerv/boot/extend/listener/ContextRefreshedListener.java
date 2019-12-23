package com.gnerv.boot.extend.listener;

import com.gnerv.boot.extend.annotation.EntityExtend;
import com.gnerv.boot.extend.store.EntityExtendBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * spring 容器内容 监听器
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/30
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        saveEntityExtendBean(contextRefreshedEvent);
    }

    /**
     * 保存带有 EntityExtend 注解的类
     *
     * @param contextRefreshedEvent spring容器
     */
    public void saveEntityExtendBean(ContextRefreshedEvent contextRefreshedEvent) {
        Map<Class, Set<EntityExtend>> ExtendBeansMap = new HashMap<>(32);
        Map<String, Object> beans = contextRefreshedEvent.getApplicationContext().getBeansWithAnnotation(EntityExtend.class);
        for (Object o : beans.values()) {
            Class<?> clazz = o.getClass();
            EntityExtend entityExtend = clazz.getAnnotation(EntityExtend.class);
            Class[] classes = entityExtend.masterEntity();
            for (Class c : classes) {
                Set<EntityExtend> entityExtends = ExtendBeansMap.get(c);
                if (StringUtils.isEmpty(entityExtends)) {
                    entityExtends = new HashSet<>();
                    entityExtends.add(entityExtend);
                    ExtendBeansMap.put(c, entityExtends);
                }else {
                    entityExtends.add(entityExtend);
                    ExtendBeansMap.put(c, entityExtends);
                }
            }
        }
        EntityExtendBean.BEANS_MAP = ExtendBeansMap;
    }

}
