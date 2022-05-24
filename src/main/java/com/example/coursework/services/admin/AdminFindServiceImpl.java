package com.example.coursework.services.admin;

import com.example.coursework.models.Admin;
import com.example.coursework.repositories.AdminRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminFindServiceImpl implements AdminFindService {
    private final AdminRepository adminRepository;

    public AdminFindServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Admin admin = adminRepository.findFirstByLogin(login);
        if (admin == null)
            throw new UsernameNotFoundException("User not found");
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ADMIN"));
        return new org.springframework.security.core.userdetails.User(login, admin.getPassword(), roles);
    }
}
