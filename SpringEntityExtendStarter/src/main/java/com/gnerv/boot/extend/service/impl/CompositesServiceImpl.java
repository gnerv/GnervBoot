package com.gnerv.boot.extend.service.impl;

import com.gnerv.boot.extend.annotation.EntityExtend;
import com.gnerv.boot.extend.service.ICompositesService;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMapping;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ResultMap 服务实现类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
@Service
public class CompositesServiceImpl implements ICompositesService {

    @Override
    public List<ResultMapping> buildHashComposites(MappedStatement ms, EntityExtend entityExtend) {
        List<ResultMapping> lists = this.buildHashComposites(ms, Object.class, entityExtend);
        return lists;
    }

    @Override
    public List<ResultMapping> buildHashComposites(MappedStatement ms, Class<?> javaType, EntityExtend entityExtend) {
        List<ResultMapping> lists = new ArrayList<>();
        String column = entityExtend.column();
        column.replace("{", "");
        column.replace("}", "");
        String[] split = column.split(",");
        for (String str : split) {
            ResultMapping resultMapping;
            String[] strings = str.split("=");
            if(strings.length == 1){
                resultMapping = new ResultMapping.Builder(ms.getConfiguration(), strings[0], strings[0], javaType)
                                .nestedQueryId(null)
                                .build();
            }else if(strings.length == 2){
                resultMapping = new ResultMapping.Builder(ms.getConfiguration(), strings[0], strings[1], javaType)
                                .nestedQueryId(null)
                                .build();
            }else {
                throw new RuntimeException("参数异常！");
            }
            lists.add(resultMapping);
        }
        return lists;
    }

    @Override
    public List<ResultMapping> buildExtendComposites(MappedStatement ms, EntityExtend entityExtend) {
        List<ResultMapping> lists = this.buildHashComposites(ms, Object.class, entityExtend);
        return lists;
    }


}

