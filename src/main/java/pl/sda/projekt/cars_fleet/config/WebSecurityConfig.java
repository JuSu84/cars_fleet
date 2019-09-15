package pl.sda.projekt.cars_fleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter  {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery("select login, password, active from employee where login=?")
                .authoritiesByUsernameQuery("select e.login, r.role from employee e inner join employee_role er" +
                        " on(e.id=er.employee_id) inner join role r on(er.role_id=r.id) where e.login=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers("/", "/csrf", "/swagger-ui.html", "/webjars/**", "/v2/api-docs**",
                        "/swagger-resources/**")
                .permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cars/*", "/employees/*", "/units/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/units/*").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/units/").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/cars/*", "/employees/*").hasRole( "ADMIN")
                .antMatchers(HttpMethod.POST, "/cars/", "/employees/*").hasRole( "ADMIN")
                .antMatchers(HttpMethod.GET, "/cars/*", "/employees/*", "/units/*").permitAll()
                .anyRequest().authenticated();


//                .authenticated().and().csrf().disable().formLogin()
//                .loginPage("/login").failureUrl("/login?error=true")
//                .defaultSuccessUrl("/admin/home")
//                .usernameParameter("username")
//                .passwordParameter("password")
          //      .and().logout()
             //   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             //   .logoutSuccessUrl("/").and().exceptionHandling()
             //   .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }


//    @Autowired
//    private DataSource dataSource;
//
////    @Autowired
////    private UserDetailsService userDetailsService;
////
////    @Bean
////    public DaoAuthenticationProvider authProvider() {
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setUserDetailsService(userDetailsService);
////        authProvider.setPasswordEncoder(passwordEncoder());
////        return authProvider;
////    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        return bCryptPasswordEncoder;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        auth.inMemoryAuthentication()
//                .withUser("user").password(passwordEncoder().encode("pass")).roles("USER")
//        .and()
//        .withUser("admin").password(passwordEncoder().encode("pass")).roles("ADMIN");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                // allows swagger public access
//                .authorizeRequests()
//                .antMatchers("/", "/csrf", "/swagger-ui.html", "/webjars/**", "/v2/api-docs**",
//                        "/swagger-resources/**")
//                .permitAll()
//                .and()
//                // allows h2 public access
//                .authorizeRequests()
////                .antMatchers("/h2-console/**").permitAll()
//                .and().headers().frameOptions().sameOrigin()
//                .and()
//                // back to app endpoints configuration
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers(HttpMethod.DELETE, "/cars/*", "/employees/*", "/units/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/units/*").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/units/").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.PUT, "/cars/*", "/employees/*").hasRole( "ADMIN")
//                .antMatchers(HttpMethod.POST, "/cars/", "/employees/*").hasRole( "ADMIN")
//                .antMatchers(HttpMethod.GET, "/cars/*", "/employees/*", "/units/*").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                // allows POST, PUT, DELETE requests from outside (postman)
//                .csrf().disable()
//                .formLogin()
//                .usernameParameter("login")
//                .passwordParameter("password");

                //.csrf().disable()
                // selects basic (header) authentication and prevents session from being created
               // .httpBasic()
              //  .and()
               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.
//                authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(AUTH_LIST).permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/registration").permitAll()
//                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
//                .authenticated().and().csrf().disable().formLogin()
//                .usernameParameter("login")
//                .passwordParameter("password")
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/").and().exceptionHandling()
//                .accessDeniedPage("/access-denied");
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//
//        auth
//                .jdbcAuthentication()
//                .usersByUsernameQuery("select login, password, active from employee where login=?")
//                .authoritiesByUsernameQuery("select e.login, r.role from employee e inner join employee_role er" +
//                        " on(e.id=er.employee_id) inner join role r on(er.role_id=r.id) where e.login=?")
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    private static final String[] AUTH_LIST = {
//            // -- swagger ui
//            "**/swagger-resources/**",
//            "/swagger-ui.html",
//            "/v2/api-docs",
//            "/webjars/**"
//    };



}
