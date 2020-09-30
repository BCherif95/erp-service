package com.tuwindi.erp.erpservice.security;

import com.tuwindi.erp.erpservice.entities.Role;
import com.tuwindi.erp.erpservice.entities.User;
import com.tuwindi.erp.erpservice.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        String[] userRoles = roles.stream().map(Role::getAuthority).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        final User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Username : " + username + " not found");
        return org.springframework.security.core.userdetails.User//
                .withUsername(user.getUsername())//
                .password(user.getPassword())
                .authorities(getAuthorities(user.getRoles()))
                .accountLocked(!user.isEnabled())
                .disabled(!user.isEnabled())
                .build();
    }
}
