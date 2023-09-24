package com.microservice.festejandoando.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
    @Value("${APP_ADMIN_NAME}")
    private String administratorName;
    @Value("${APP_ADMIN_PASSWORD}")
    private String administratorPassword;
    

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID"))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/topic/list")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/topic/find/{id}")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/article/listByTopic/{id}")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/article//listSuggestedArticlesByTopic/{id}")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/booking/save")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/client/save")).permitAll()
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults())
            .formLogin(formLogin -> formLogin
                .disable());
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

    public void initAdmin() {
        String encodedPassword = passwordEncoder().encode(administratorPassword);
        String username = administratorName;
        Administrator admin = new Administrator();
        admin.setActive(true);
        admin.setPassword(encodedPassword);
        admin.setUsername(username);
        if(adminUserDetailsServiceImpl.findByUsername(username) == null){ 
            adminUserDetailsServiceImpl.save(admin);
        };
    }
}

    





