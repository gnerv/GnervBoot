package com.gnerv.boot.extend.service.impl;

import com.gnerv.boot.extend.annotation.EntityExtend;
import com.gnerv.boot.extend.service.IConfigurationService;
import com.gnerv.boot.extend.service.IMappedStatementService;
import com.gnerv.boot.extend.service.IResultMapService;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * MappedStatement 服务实现
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/30
 */
@Service
public class MappedStatementServiceImpl implements IMappedStatementService {

    @Resource
    IResultMapService resultMapService;

    @Resource
    IConfigurationService configurationService;

    @Override
    public MappedStatement buildMappedStatement(MappedStatement ms, Set<EntityExtend> entityExtends) {
        Configuration configuration = configurationService.buildConfiguration(ms, entityExtends);
        MappedStatement.Builder builder = new MappedStatement.Builder(configuration, ms.getId(), ms.getSqlSource(), ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        List<ResultMap> resultMaps = resultMapService.buildResultMap(ms, entityExtends);

        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        return builder.build();

    }
}
