package test.java.testjava.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TokenUserDetailsImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("ali".equals(username)) {
            return new User("ali","$2a$12$sxEYZ5eXk/N6JZa.EP142eDKGApg/KBu4zeUsE88JAApR8QH/SWiq",new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username " + username);
        }
    }
}
