package hu.sze.szakdolgozat.market.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.sze.szakdolgozat.market.service.UserDetailsServiceImp;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

       //  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable();
        http.httpBasic().and()
        .authorizeRequests()
        .antMatchers("/api/auth/**")
        .authenticated().anyRequest().permitAll().and().formLogin();
    }

}