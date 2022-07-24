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
    private CorsConfigurationSource configurationSource()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader(config.ALL);
        config.addAllowedHeader(config.ALL);
        config.addAllowedMethod(config.ALL);
        source.registerCorsConfiguration("/**", config);
        return source;
    }
//    uncomment when deploy to heroku
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        CustomizeFilterConfig customAuthenticationFilter = new CustomizeFilterConfig(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
//        uncomment when deploy to heroku
        http.cors().configurationSource(configurationSource()).and()
                .requiresChannel()
                .anyRequest()
                .requiresSecure();
//        uncomment when deploy to Heroku
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers(
                "/user/register",
                "/user/register-seller",
                "/user/display-all",
                "/user/display-by-id/{user_id}",
                "/user/profile/{username}",
                "/user/verification",
                "/user/edit/{userId}",
                "/user/delete-user/{user_id}",
                "/city/**",
                "/swagger-ui.html/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/webjars/**",
                "/v2/api-docs",
                "carousel/display-all"
        ).permitAll();
        http.authorizeRequests().antMatchers(
                "/category/all",
                "/category/show/{id}"
        ).permitAll();
        http.authorizeRequests().antMatchers(
                "/product/all/**",
                "/product/filter-by/**",
                "/product/latest",
                "/product/related/{id}",
                "/product/user/{username}",
                "/product/detail/{id}",
                "/bid/check/{userId}/{productId}",
                "/bid/buyer/all/{id}",
                "/bid/seller/all/{id}",
                "/bid/detail/{id}"
        ).permitAll();
        http.authorizeRequests().antMatchers(
                "/wishlist/check/{userId}/{productId}",
                "/wishlist/all/{id}",
                "/wishlist/mini/{id}"
        ).permitAll();
        http.authorizeRequests().antMatchers(
                "/notification/buyer/all/{id}",
                "/notification/buyer/mini/{id}",
                "/notification/seller/all/{id}",
                "/notification/seller/mini/{id}"
        ).permitAll();
        http.authorizeRequests().antMatchers("/login/**").permitAll();
        http.authorizeRequests().antMatchers(
                "/bid/buyer/add",
                        "/wishlist/add",
                        "/notification/add",
                        "/buyer/delete/{id}",
                        "/wishlist/delete/{id}",
                        "/wishlist/delete-all/{id}",
                        "/notification/buyer/delete/{id}",
                        "/notification/buyer/delete-all/{id}"
                )
                .hasAnyAuthority("BUYER");
        http.authorizeRequests().antMatchers(
                "/product/add",
                        "/product/delete/{productId}",
                        "/notification/add",
                        "/notification/seller/delete/{id}",
                        "/notification/seller/delete-all/{id}"
                )
                .hasAnyAuthority("SELLER");
        http.authorizeRequests().antMatchers(
                "/admin/**",
                "/category/add",
                "/category/edit/{id}",
                "/category/delete/{id}",
                "/carousel/add",
                "/carousel/edit/{carouselId}",
                        "/carousel/edit/{carouselId}"
                        )
                .hasAnyAuthority("ADMIN");
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
