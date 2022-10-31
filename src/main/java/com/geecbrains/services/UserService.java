package com.geecbrains.services;


import com.geecbrains.entities.Autorites;
import com.geecbrains.entities.User;
import com.geecbrains.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // находим пользователя в базе данных
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getAutorites()));
    }
    // это метод перепаковывает нашего изера по имени роли в юзера для spring security
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Autorites> autorites) {
        return autorites.stream().map(autorite -> new SimpleGrantedAuthority(autorite.getName())).collect(Collectors.toList());
    }
}