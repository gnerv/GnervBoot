package com.gnerv.boot.extend.utils.mapper;

import org.apache.ibatis.mapping.MappedStatement;

/**
 * <p>
 * Mapper工具类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public class MapperUtils {

    public final static String EXTEND_MAP = ".ExtendMap";

    private MapperUtils() {
    }

    public static String buildExtendResultMapId(MappedStatement ms) {
        String msId = ms.getId();
        String substring = msId.substring(0, msId.lastIndexOf("."));
        return substring + EXTEND_MAP;
    }
}
