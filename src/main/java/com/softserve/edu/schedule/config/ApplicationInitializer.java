/* ApplicationInitializer 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
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
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ApplicationConfig.class, TaskExecutorsConfig.class,
                MailSendingConfig.class, SocialConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext servletAppContext = new AnnotationConfigWebApplicationContext();
        servletAppContext.register(WebConfig.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(
                servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);

        ServletRegistration.Dynamic dispatcher = servletContext
                .addServlet("dispatcher", dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
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
        return new Class[] {WebSecurityConfig.class};
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
        return new Class<?>[0];
    }

    /**
     * Specify the servlet mapping(s) for the DispatcherServlet.
     *
     * @return list of servlet mappings in array
     */
    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

}
