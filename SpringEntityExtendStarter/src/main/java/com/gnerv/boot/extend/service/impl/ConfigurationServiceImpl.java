package com.gnerv.boot.extend.service.impl;

import com.gnerv.boot.extend.annotation.EntityExtend;
import com.gnerv.boot.extend.service.IConfigurationService;
import com.gnerv.boot.extend.service.IResultMapService;
import com.gnerv.boot.extend.utils.mapper.MapperUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * <p>
 * ResultMap 服务实现类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
@Service
public class ConfigurationServiceImpl implements IConfigurationService {

    @Resource
    IResultMapService resultMapService;

    @Override
    public Configuration buildConfiguration(MappedStatement ms, Set<EntityExtend> entityExtends) {
        Configuration configuration = ms.getConfiguration();
        boolean b = configuration.hasResultMap(MapperUtils.buildExtendResultMapId(ms));
        if (!b) {
            ResultMap resultMap = resultMapService.buildExtendResultMap(ms, entityExtends);
            configuration.addResultMap(resultMap);
        }
        return configuration;
    }

}
