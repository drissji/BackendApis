package net.todoapp.todoapp.service;

import net.todoapp.todoapp.Entity.AppUser;
import net.todoapp.todoapp.Entity.Roles;
import net.todoapp.todoapp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AppUser user = userRepository.findAppUserByUserName(username);


        List<Roles> roles = user.getAuthorities();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        roles.forEach(roles1 -> grantedAuthorities.add(new SimpleGrantedAuthority(roles1.getRoleName())));


        return new User(user.getUserName(),user.getUserPassword(),grantedAuthorities);
    }
}
