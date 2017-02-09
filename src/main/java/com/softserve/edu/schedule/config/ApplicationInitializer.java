/* ApplicationInitializer 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Main start initializer of application. Everything starts here.
 *
 * @version 1.0 07 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
public class ApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Configure the given ServletContext with any servlets, filters, listeners
     * context-params and attributes necessary for initializing this web
     * application.
     *
     * @param servletContext
     *            the ServletContext to initialize
     * @throws ServletException
     *             if any call against the given ServletContext throws a
     *             ServletException
     */
    @Override
    public void onStartup(final ServletContext servletContext)
            throws ServletException {
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter(
                ConfigConstants.ENCODING_FILTER_NAME,
                new CharacterEncodingFilter());
        encodingFilter.setInitParameter(
                ConfigConstants.ENC_FILTER_ENCODING_PARAM,
                ConfigConstants.UTF8);
        encodingFilter.setInitParameter(
                ConfigConstants.ENC_FILTER_FORCE_ENCODING_PARAM,
                ConfigConstants.FORCE_ENCODING_ENABLED);
        encodingFilter.addMappingForUrlPatterns(null, false,
                ConfigConstants.ENC_FILTER_URL_MAPPING_PATTERN);
        super.onStartup(servletContext);
    }

    /**
     * Optionally perform further registration customization once
     * registerDispatcherServlet(ServletContext) has completed.
     * 
     * @param registration
     *            the DispatcherServlet registration to be customized
     */
    @Override
    protected void customizeRegistration(
            final ServletRegistration.Dynamic registration) {
        registration.setInitParameter(
                ConfigConstants.THROW_EXC_IF_NO_HANDLER_FOUND,
                ConfigConstants.THROW_EXC_IF_NO_HANDLER_FOUND_STATUS);
    }

    /**
     * Specify Configuration and/or Component annotated classes to be provided
     * to the root application context.
     *
     * @return the configuration classes for the root application context, or
     *         null if creation and registration of a root context is not
     *         desired
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {ApplicationConfig.class, TaskExecutorsConfig.class,
                MailSendingConfig.class};
    }

    /**
     * Specify Configuration and/or Component annotated classes to be provided
     * to the dispatcher servlet application context.
     *
     * @return the configuration classes for the dispatcher servlet application
     *         context or null if all configuration is specified through root
     *         config classes.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ApplicationConfig.class, WebConfig.class};
    }

    /**
     * Specify the servlet mapping(s) for the DispatcherServlet.
     *
     * @return list of servlet mappings in array
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {ConfigConstants.START_URL};
    }

}
