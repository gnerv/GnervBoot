package com.gnerv.boot.extend.service;

import com.gnerv.boot.extend.annotation.EntityExtend;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMapping;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * ResultMap 服务类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public interface IResultMappingService {

    List<ResultMapping> buildResultMapping(MappedStatement ms, Set<EntityExtend> entityExtends);

    List<ResultMapping> buildResultMapping(MappedStatement ms, Class<?> javaType, Set<EntityExtend> entityExtends);

}
