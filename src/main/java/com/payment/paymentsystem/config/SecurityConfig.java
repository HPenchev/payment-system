package com.payment.paymentsystem.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Class configuring security.
 *
 * @author Hristo Penchev
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * <p>Method configuring security.
     * <a href="http://www.supermanisthegreatest.com">Superman!</a>
     * </p>
     * @param http htpp
     * @return security config
     * @since 1.0
     */
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http)
            throws Exception {
        http.httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests((authorize) -> authorize
                .anyRequest().authenticated()
        )
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
