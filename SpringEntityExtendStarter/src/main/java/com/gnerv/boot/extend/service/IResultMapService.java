package com.gnerv.boot.extend.service;

import com.gnerv.boot.extend.annotation.EntityExtend;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;

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
public interface IResultMapService {

    List<ResultMap> buildResultMap(MappedStatement ms, Set<EntityExtend> entityExtends);

    ResultMap buildExtendResultMap(MappedStatement ms, Set<EntityExtend> entityExtends);

}
