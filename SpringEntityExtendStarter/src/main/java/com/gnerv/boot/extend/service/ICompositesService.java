package com.gnerv.boot.extend.service;

import com.gnerv.boot.extend.annotation.EntityExtend;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMapping;

import java.util.List;

/**
 * <p>
 * ResultMap 服务类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public interface ICompositesService {

    List<ResultMapping> buildHashComposites(MappedStatement ms, EntityExtend entityExtend);

    List<ResultMapping> buildHashComposites(MappedStatement ms, Class<?> javaType, EntityExtend entityExtend);

    List<ResultMapping> buildExtendComposites(MappedStatement ms, EntityExtend entityExtend);

}
