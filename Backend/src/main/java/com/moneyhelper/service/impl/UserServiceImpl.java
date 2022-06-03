/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.service.impl;

import com.moneyhelper.dto.UserDto;
import com.moneyhelper.model.Auth;
import com.moneyhelper.model.User;
import com.moneyhelper.repository.AuthRepository;
import com.moneyhelper.repository.UserRepository;
import com.moneyhelper.service.UserService;
import com.moneyhelper.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static com.moneyhelper.util.MoneyHelperUtil.getGrantedAuthorities;
import static java.lang.String.format;

@Primary
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            final UserRepository userRepository,
            final AuthRepository authRepository,
            final UserMapper userMapper,
            final PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(final String userEmail) throws UsernameNotFoundException {
        final User user = getUserByEmail(userEmail);
        final Collection<? extends GrantedAuthority> roles = getGrantedAuthorities(Collections.singleton(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), roles);
    }

    @Override
    public void logoutUser(final String userEmail) {
        final User user = getUserByEmail(userEmail);
        user.getAuth().setAccessToken(null);
        user.getAuth().setRefreshToken(null);
        userRepository.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void save(final UserDto userDto) {
        final User user = userMapper.toUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        final Auth auth = authRepository.save(new Auth());
        user.setAuth(auth);
        userRepository.save(user);
    }

    @Override
    public void editAuth(final String userEmail, final String accessToken, final String refreshToken) {
        final User user = getUserByEmail(userEmail);
        user.getAuth().setAccessToken(accessToken);
        user.getAuth().setRefreshToken(refreshToken);
        userRepository.save(user);
    }

    @Override
    public void edit(final UserDto userDto, final String userId) {
        final User user = userMapper.toUser(userDto);
        user.setId(userId);
        userRepository.save(user);
    }

    private User getUserByEmail(final String userEmail) {
        final Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(format("Failed to find user with email [%s]", userEmail));
        }
        return userOptional.get();
    }

}
