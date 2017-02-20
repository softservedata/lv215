/* WebConfig 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import java.util.Locale;

import org.apache.commons.codec.CharEncoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Configuration of web MVC part of application.
 *
 * @version 1.0 07 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Configuration
@ComponentScan(value = "com.softserve.edu.schedule",
        excludeFilters = @Filter(IgnoreDuringScan.class))
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    /**
     * Resources handlers cache period.
     */
    private static final int RESOURCES_CACHE_PERIOD = 3600;

    /**
     * Set configured view resolver for application.
     *
     * @return UrlBasedViewResolver instance
     */
    @Bean
    public UrlBasedViewResolver viewResolver() {
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);
        return viewResolver;
    }

    /**
     * Set Apache tiles configurer for application.
     *
     * @return TilesConfigurer instance
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/defs/general.xml");
        return tilesConfigurer;
    }

    /**
     * Set multipart files resolver for application.
     *
     * @return CommonsMultipartResolver instance
     */
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    /**
     * Set session locale resolver for application.
     *
     * @return SessionLocaleResolver instance
     */
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    /**
     * Set internalization message source for application.
     *
     * @return ResourceBundleMessageSource instance
     */
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("/i118n/messages");
        messageSource.setDefaultEncoding(CharEncoding.UTF_8);
        return messageSource;
    }

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations. Interceptors can be registered to apply to
     * all requests or be limited to a subset of URL patterns.
     *
     * @param registry
     *            an interceptors registry of application
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }

    /**
     * Add handlers to serve static resources such as images, js, and, css files
     * from specific locations under web application root, the classpath, and
     * others.
     *
     * @param registry
     *            an resource handlers registry of application
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(RESOURCES_CACHE_PERIOD);
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/images/**")
                .setCachePeriod(RESOURCES_CACHE_PERIOD);
    }

}
