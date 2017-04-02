package dk.mhr.application.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by mortenrummelhoff on 11/04/16.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestAuthenticationHandler restAuthenticationHandler;
    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("Configure HttpSecurity Override method callback");
        http.cors();
        http.csrf().disable();
                http.authorizeRequests()
                        .antMatchers("/chat/**").permitAll()
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        //.antMatchers("/login").permitAll()
                        .antMatchers("/api/**").authenticated();
                //.anyRequest().authenticated().and().addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().successHandler(restAuthenticationHandler);
        http.formLogin().failureHandler(new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

                logger.info("onAuthenticationFailure called: " + exception);
                super.onAuthenticationFailure(request, response, exception);
            }
        })
                //.loginPage("/login")
                //.permitAll()
//                .and()
//                .logout()
//                .permitAll()
        ;
                //http.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class);

    }


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        logger.info("configureGlobal called");

        builder.inMemoryAuthentication()
                .withUser("morten@mhr.dk").password("Morten1024!").roles("ADMIN").and()
        .withUser("mhr").password("mhr").roles("USER");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    //@Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        logger.info("configureGlobal called");
//
//        auth.inMemoryAuthentication()
//                .withUser("morten@mhr.dk").password("Morten1024!").roles("ADMIN");
//    }
}
