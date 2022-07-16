package com.app.SecondGadgetApp.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private final UserDetailsService userDetailsService;
     @Autowired
     private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    uncomment when deploy to heroku
//    private CorsConfigurationSource configurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader(config.ALL);
//        config.addAllowedHeader(config.ALL);
//        config.addAllowedMethod(config.ALL);
//        source.registerCorsConfiguration("/**", config);
//        return source;
//    }
//    uncomment when deploy to heroku
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        CustomizeFilterConfig customAuthenticationFilter = new CustomizeFilterConfig(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
//        uncomment when deploy to heroku
//        http.cors().configurationSource(configurationSource()).and()
//                .requiresChannel()
//                .anyRequest()
//                .requiresSecure();
//        uncomment when deploy to Heroku
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(
                "/user/register",
                "/user/registration-seller",
                "/user/display-all",
                "/user/display-by-id/{user_id}",
                "/user/profile/{username}",
                "/user/verification",
                "/user/update-user/{userId}",
                "/user/delete-user/{user_id}",
                "/city/**",
                "/swagger-ui.html/**"
        ).permitAll();
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers("/product/display-all").hasAnyAuthority("BUYER");
        http.authorizeRequests().antMatchers("/product/add").hasAnyAuthority("SELLER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomizeAuthorFilterConfig(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/*", "/configuration/", "/swagger-ui.html", "/webjars/*");
    }
}
