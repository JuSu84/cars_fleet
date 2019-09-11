package pl.sda.projekt.cars_fleet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser("user").password(encoder.encode("pass")).roles("USER")
        .and()
        .withUser("admin").password(encoder.encode("pass")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // allows swagger public access
                .authorizeRequests()
                .antMatchers("/", "/csrf", "/swagger-ui.html", "/webjars/**", "/v2/api-docs**", "/swagger-resources/**")
                .permitAll()
                .and()
                // allows h2 public access
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .and().headers().frameOptions().sameOrigin()
                .and()
                // back to app endpoints configuration
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/cars/*", "/employees/*", "/units/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/units/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/units/").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/cars/*", "/employees/*").hasRole( "ADMIN")
                .antMatchers(HttpMethod.POST, "/cars/", "/employees/*").hasRole( "ADMIN")
                .antMatchers(HttpMethod.GET, "/cars/*", "/employees/*", "/units/*").permitAll()
                .anyRequest().authenticated()
                .and()
                // allows POST, PUT, DELETE requests from outside (postman)
                .csrf().disable()
                // selects basic (header) authentication and prevents session from being created
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        ;
    }
}
