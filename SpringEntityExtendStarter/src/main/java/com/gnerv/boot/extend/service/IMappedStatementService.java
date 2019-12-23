package com.gnerv.boot.extend.service;

import com.gnerv.boot.extend.annotation.EntityExtend;
import org.apache.ibatis.mapping.MappedStatement;

import java.util.Set;

/**
 * <p>
 * MappedStatement 服务
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/8/30
 */
public interface IMappedStatementService {

    MappedStatement buildMappedStatement(MappedStatement ms, Set<EntityExtend> entityExtends);

}