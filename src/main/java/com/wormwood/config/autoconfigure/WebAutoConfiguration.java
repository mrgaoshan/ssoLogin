package com.wormwood.config.autoconfigure;

import com.wormwood.config.BaseRestExceptionHandler;
import com.wormwood.config.RedirectFilterConfig;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Created by Dean on 2016/12/30.
 */
@ConditionalOnWebApplication
@AutoConfigureBefore(WebMvcAutoConfiguration.class)
@Import({RedirectFilterConfig.class, BaseRestExceptionHandler.class})
public class WebAutoConfiguration {
}
