package com.geecbrains.configur;


import com.geecbrains.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserService userService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Dao Authentication Provider");
        http.authorizeRequests()
                // ползователь который захоит "/auth_page/**" - пользователь должен быть аутентифицированн
                .antMatchers("/auth_page/**").authenticated()
                .antMatchers("/user_info").authenticated()
                // у пользователя должны быть роли
                .antMatchers("/admin/**").hasAnyRole("ADMIN", "SUPERADMIN") // ROLE_ADMIN, ROLE_SUPERADMIN
            // может быть доступ по правам.
                .antMatchers("/read/**").hasAnyAuthority("READ")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
//                .and()
//                .sessionManagement()
//                .maximumSessions(1)
//                .maxSessionsPreventsLogin(true);
    }
    // хеширование паролей
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    // DaoAuthenticationProvider ищем права доступа в базе
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }
}