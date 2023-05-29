package com.microservice.festejandoando.service;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.microservice.festejandoando.model.Administrator;
import com.microservice.festejandoando.repository.IAdminRepository;


@Service
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IAdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Administrator admin = adminRepository.findByUsername(username);
  
        if (admin == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
            admin.getUsername(),
            admin.getPassword(), Collections.singletonList(() -> "ROLE_ADMIN")
        );
    }

    public Administrator findByUsername(String username){
        return adminRepository.findByUsername(username);
    }

    public Administrator save(Administrator admin){
       return adminRepository.save(admin);        
    }
}
