package io.recruitment.assessment.api.security;

import io.recruitment.assessment.api.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(username)
                .map(user ->
                        new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getUserType()))
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("No user with username %s was not found!", username)));
    }
}
