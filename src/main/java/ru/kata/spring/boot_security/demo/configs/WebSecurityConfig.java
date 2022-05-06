package ru.kata.spring.boot_security.demo.configs;


//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
//
//
////класс SecurityConfig - настройка секьюрности по определенным URL,
//// а также настройка UserDetails и GrantedAuthority
//@Configuration
////@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserServiceImpl userDetailsService;
//    private SuccessUserHandler successUserHandler;
//
//    public WebSecurityConfig(UserServiceImpl userDetailsService, SuccessUserHandler successUserHandler) {
//        this.userDetailsService = userDetailsService;
//        this.successUserHandler = successUserHandler;
//    }
//
//
//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        // конфигурация для прохождения аутентификации
//        //тут для аутентификации нам нужно чтобы юзердитейлс информацию передавал
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.formLogin()
//                //.loginPage("/login")
//                .successHandler(successUserHandler)
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .permitAll();
//
//        http.logout()
//                // разрешаем делать логаут всем
//                .permitAll()
//                // указываем URL логаута
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                // указываем URL при удачном логауте
//                .logoutSuccessUrl("/login?logout")
//                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
//                .and().csrf().disable();
//
//        http
//                // делаем страницу регистрации недоступной для авторизированных пользователей
//                .authorizeRequests()
//                //страницы аутентификаци доступна всем
//                .antMatchers("/login").anonymous()
//                // защищенные URL
//                .antMatchers("/admin").access("hasAnyRole('ADMIN')")
//                .antMatchers("/admin/**").access("hasAnyRole('ADMIN')")
//                .antMatchers("/user").access("hasAnyRole('USER', 'ADMIN')")
//                .antMatchers("/user/**").access("hasAnyRole('USER', 'ADMIN')");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}

//
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private SuccessUserHandler successUserHandler;

    private UserServiceImpl userDetailsService;


@Autowired
    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserServiceImpl userDetailsService) {
        this.successUserHandler = successUserHandler;
        this.userDetailsService = userDetailsService;
    }


    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // конфигурация для прохождения аутентификации
        //тут для аутентификации нам нужно чтобы юзердитейлс информацию передавал
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http


                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin").access("hasAnyRole('ADMIN')")
                .antMatchers("/admin/**").access("hasAnyRole('ADMIN')")
                .antMatchers("/user").access("hasAnyRole('USER', 'ADMIN')")
                .antMatchers("/user/**").access("hasAnyRole('USER', 'ADMIN')")

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .successHandler(successUserHandler)
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }

//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider; }
//@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.userDetailsService(userDetailsService);
//    }

}

    // аутентификация inMemory
//    @Bean
//    @Override
//    public UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(User.builder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build(),
//
//                User.builder()
//                        .username("user")
//                        .password("user")
//                        .roles("USER")
//                        .build()
//
//
//        );
//    }
