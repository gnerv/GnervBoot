package com.gnerv.boot.extend.service.impl;

import com.gnerv.boot.extend.annotation.EntityExtend;
import com.gnerv.boot.extend.config.ExtendConfigProperties;
import com.gnerv.boot.extend.service.IResultMappingService;
import com.gnerv.boot.extend.utils.mapper.MapperUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMapping;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class ResultMappingServiceImpl implements IResultMappingService {

    @Resource
    ExtendConfigProperties extendConfigProperties;

    @Override
    public List<ResultMapping> buildResultMapping(MappedStatement ms, Set<EntityExtend> entityExtends) {
        List<ResultMapping> lists = this.buildResultMapping(ms, Object.class, entityExtends);
        return lists;
    }

    @Override
    public List<ResultMapping> buildResultMapping(MappedStatement ms, Class<?> javaType, Set<EntityExtend> entityExtends) {
        List<ResultMapping> resultMappings = ms.getResultMaps().get(0).getResultMappings();
        ResultMapping resultMapping =
                new ResultMapping.Builder(ms.getConfiguration(), extendConfigProperties.getName(), null, HashMap.class)
                        .nestedResultMapId(MapperUtils.buildExtendResultMapId(ms))
                        .build();
        List lists = new ArrayList(resultMappings);
        lists.add(resultMapping);
        return lists;
    }

}
