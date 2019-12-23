package com.gnerv.boot.extend.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 基础平台-自动化配置启动类
 * </p>
 *
 * @author Gnerv LiGen
 * @since 2019-08-18
 */
@Configuration
@EnableConfigurationProperties(ExtendConfigProperties.class)
@ConditionalOnWebApplication
public class EnableAutoConfiguration {

}
