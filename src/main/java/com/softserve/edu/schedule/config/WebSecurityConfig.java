/* WebSecurityConfig 1.0 02/07/2017 */
package com.softserve.edu.schedule.config;

import org.apache.commons.codec.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Configuration of Spring Security part of application.
 *
 * @version 1.0 07 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("com.softserve.edu.schedule")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Remember me token validity period.
     */
    private static final int REMEMBER_ME_TOKEN_LIFE_TIME = 2592000;

    /**
     * BCrypt password encoder password strength.
     */
    private static final int BC_CRYPT_PASS_STRENGTH = 10;

    /**
     * UserDetailsService instance.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Configure the HttpSecurity settings of application.
     *
     * @param http
     *            the HttpSecurity to modify
     * @throws Exception
     *             if an error occurs
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(CharEncoding.UTF_8);
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.requiresChannel().anyRequest().requiresSecure().and().formLogin()
                .loginPage("/").loginProcessingUrl("/login")
                .failureUrl("/login?fail=true").and().logout()
                .logoutSuccessUrl("/").and().rememberMe().key("Scheduler_app")
                .userDetailsService(userDetailsService())
                .tokenValiditySeconds(REMEMBER_ME_TOKEN_LIFE_TIME).and()
                .apply(new SpringSocialConfigurer());
    }

    /**
     * Set configured password encoder for application.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(BC_CRYPT_PASS_STRENGTH);
    }

    /**
     * Specify the AuthenticationManager for security.
     *
     * @param auth
     *            the AuthenticationManagerBuilder to use
     *
     * @throws Exception
     *             if error occurs
     */
    @Override
    protected void configure(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
