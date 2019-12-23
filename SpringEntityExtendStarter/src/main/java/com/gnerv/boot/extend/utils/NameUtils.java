package com.gnerv.boot.extend.utils;

import com.gnerv.boot.extend.annotation.EntityExtend;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 名称工具类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019/5/31
 */
public class NameUtils {

    private NameUtils() {
    }

    public static String lowerCamelCase(String name) {
        char[] chars = name.toCharArray();
        chars[0] = String.valueOf(chars[0]).toLowerCase().charAt(0);
        return String.valueOf(chars);
    }

    public static String lowerCamelCase(EntityExtend entityExtend) {
        String mapKey = entityExtend.mapKey();
        if (StringUtils.isEmpty(mapKey)) {
            mapKey = entityExtend.resultType().getSimpleName();
        }
        return lowerCamelCase(mapKey);
    }
}
