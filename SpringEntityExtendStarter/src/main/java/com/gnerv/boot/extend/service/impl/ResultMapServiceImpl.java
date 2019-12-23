package com.gnerv.boot.extend.service.impl;

import com.gnerv.boot.extend.annotation.EntityExtend;
import com.gnerv.boot.extend.service.ICompositesService;
import com.gnerv.boot.extend.service.IResultMapService;
import com.gnerv.boot.extend.service.IResultMappingService;
import com.gnerv.boot.extend.utils.NameUtils;
import com.gnerv.boot.extend.utils.mapper.MapperUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
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
public class ResultMapServiceImpl implements IResultMapService {

    @Resource
    IResultMappingService resultMappingService;

    @Resource
    ICompositesService compositesService;

    @Override
    public List<ResultMap> buildResultMap(MappedStatement ms, Set<EntityExtend> entityExtends) {
        List<ResultMap> lists = new ArrayList<>(8);
        ResultMap resultMap = ms.getResultMaps().get(0);
        List<ResultMapping> resultMappings = resultMappingService.buildResultMapping(ms, entityExtends);
        ResultMap build = new ResultMap.Builder(ms.getConfiguration(), resultMap.getId(), resultMap.getType(), resultMappings).build();
        lists.add(build);
        return lists;
    }

    @Override
    public ResultMap buildExtendResultMap(MappedStatement ms, Set<EntityExtend> entityExtends) {
        List<ResultMapping> resultMappings = new ArrayList<>(8);
        for (EntityExtend entityExtend : entityExtends) {
            List<ResultMapping> composites = compositesService.buildExtendComposites(ms, entityExtend);
            ResultMapping resultMapping =
                    new ResultMapping.Builder(ms.getConfiguration(), NameUtils.lowerCamelCase(entityExtend), entityExtend.column(), Object.class)
                            .nestedQueryId(entityExtend.selectMethod())
                            .composites(composites)
                            .build();
            resultMappings.add(resultMapping);
        }
        ResultMap build = new ResultMap.Builder(ms.getConfiguration(), MapperUtils.buildExtendResultMapId(ms), HashMap.class, resultMappings).build();
        return build;
    }

}
