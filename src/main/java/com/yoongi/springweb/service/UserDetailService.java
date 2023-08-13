package com.yoongi.springweb.service;

import com.yoongi.springweb.domain.User;
import com.yoongi.springweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException((id)));
    }
}
