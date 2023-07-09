package com.microservice.festejandoando.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.microservice.festejandoando.model.Administrator;
import com.microservice.festejandoando.service.AdminUserDetailsServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends GlobalAuthenticationConfigurerAdapter  {

    @Autowired
    private AdminUserDetailsServiceImpl adminUserDetailsServiceImpl;
    

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID").and()
            .authorizeHttpRequests((requests) -> requests
            .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api")).permitAll()
            .anyRequest().authenticated()).formLogin().defaultSuccessUrl("/home").and()
            .httpBasic();
      return http.build();
    }
  
    @Bean
    public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(8);
    }

   
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailsServiceImpl).passwordEncoder(passwordEncoder());
        initAdmin();
    }

    //We save an admin for default
    public void initAdmin() {
        String encodedPassword = passwordEncoder().encode("123");
        String username = "admin";
        Administrator admin = new Administrator();
        admin.setActive(true);
        admin.setPassword(encodedPassword);
        admin.setUsername(username);
        if(adminUserDetailsServiceImpl.findByUsername(username) == null){ 
            adminUserDetailsServiceImpl.save(admin);
        };
    }
}

    





