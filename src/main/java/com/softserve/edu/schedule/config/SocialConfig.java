/* SocialConfig 1.0 02/18/2017 */
package com.softserve.edu.schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import javax.sql.DataSource;

/**
 * Social network APIs configuration of application.
 *
 * @version 1.0 18 February 2017
 *
 * @author Petro Zelyonka
 *
 * @since 1.8
 */
@Configuration
@PropertySource({"/WEB-INF/social.properties"})
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    /**
     * Data source instance for storage users connections.
     */
    @Autowired
    private DataSource dataSource;

    /**
     * Callback method to allow configuration of ConnectionFactorys.
     *
     * @param cfConfig
     *            A configurer for adding ConnectionFactory instances.
     * @param env
     *            The Spring environment, useful for fetching application
     *            credentials needed to create a ConnectionFactory instance.
     */
    @Override
    public void addConnectionFactories(
            final ConnectionFactoryConfigurer cfConfig, final Environment env) {
        FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(
                env.getProperty("facebook.app.id"),
                env.getProperty("facebook.app.secret"));
        facebookConnectionFactory.setScope("public_profile,email");
        cfConfig.addConnectionFactory(facebookConnectionFactory);

        GoogleConnectionFactory googleConnectionFactory = new GoogleConnectionFactory(
                env.getProperty("google.app.id"),
                env.getProperty("google.app.secret"));
        googleConnectionFactory.setScope("profile email");
        cfConfig.addConnectionFactory(googleConnectionFactory);

    }

    /**
     * Callback method to enable creation of a UserIdSource that uniquely
     * identifies the current user.
     *
     * @return the UserIdSource.
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    /**
     * Callback method to create an instance of UsersConnectionRepository. Will
     * be used to create a request-scoped instance of ConnectionRepository for
     * the current user.
     *
     * @param connectionFactoryLocator
     *            A ConnectionFactoryLocator to be used by the
     *            UsersConnectionRepository.
     * @return An instance of UsersConnectionRepository.
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(
            final ConnectionFactoryLocator connectionFactoryLocator) {
        return new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
    }

    /**
     * Create an instance of ConnectController for managing the
     * account-to-service-provider connection flow.
     *
     * @param connectionFactoryLocator
     *            A ConnectionFactoryLocator to be used by the
     *            ConnectController.
     *
     * @param connectionRepository
     *            A ConnectionRepository instance to persist user social
     *            connections.
     *
     * @return An instance of ConnectController.
     */
    @Bean
    public ConnectController connectController(
            final ConnectionFactoryLocator connectionFactoryLocator,
            final ConnectionRepository connectionRepository) {
        return new ConnectController(connectionFactoryLocator,
                connectionRepository);
    }

    /**
     * Create an instance of ProviderSignInUtils for support provider user
     * sign-in scenarios.
     *
     * @param connectionFactoryLocator
     *            A ConnectionFactoryLocator to be used by the
     *            ProviderSignInUtils.
     *
     * @return An instance of ProviderSignInUtils.
     */
    @Bean
    public ProviderSignInUtils getProviderSignInUtils(
            final ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator,
                getUsersConnectionRepository(connectionFactoryLocator));
    }

}
