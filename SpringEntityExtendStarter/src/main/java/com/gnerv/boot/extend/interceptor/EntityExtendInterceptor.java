package com.gnerv.boot.extend.interceptor;

import com.gnerv.boot.extend.annotation.EntityExtend;
import com.gnerv.boot.extend.service.IMappedStatementService;
import com.gnerv.boot.extend.store.EntityExtendBean;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * <p>
 * sql拦截器
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Component
public class EntityExtendInterceptor implements Interceptor {

    @Resource
    IMappedStatementService mappedStatementService;

    @Override
    public Object intercept(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        List<ResultMap> resultMaps = ms.getResultMaps();
        for (ResultMap resultMap : resultMaps) {
            Class<?> type = resultMap.getType();
            Set<EntityExtend> entityExtends = checkEntityExtend(type);
            if(!StringUtils.isEmpty(entityExtends)){
                MappedStatement mappedStatement = mappedStatementService.buildMappedStatement(ms, entityExtends);
                args[0] = mappedStatement;
            }
        }
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return target instanceof Executor ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {
    }

    private Set<EntityExtend> checkEntityExtend(Class clazz){
        Set<EntityExtend> entityExtends = EntityExtendBean.BEANS_MAP.get(clazz);
        return entityExtends;
    }
}
