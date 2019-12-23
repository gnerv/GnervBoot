package com.gnerv.boot.extend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 基础平台-自动化配置类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019-08-18
 */
@Data
@ConfigurationProperties(prefix = "gnerv.boot.extend")
public class ExtendConfigProperties {

    private String name = "extend";

}
