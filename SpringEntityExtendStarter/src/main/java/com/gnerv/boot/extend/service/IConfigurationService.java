package com.gnerv.boot.extend.service;

import com.gnerv.boot.extend.annotation.EntityExtend;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;

import java.util.Set;

/**
 * <p>
 * ResultMap 服务类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public interface IConfigurationService {

    Configuration buildConfiguration(MappedStatement ms, Set<EntityExtend> entityExtends);

}
