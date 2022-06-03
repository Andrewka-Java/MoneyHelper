/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.service;

import com.moneyhelper.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void logoutUser(String userEmail);

    void save(UserDto userDto);

    void editAuth(String userEmail, String accessToken, String refreshToken);

    void edit(UserDto userDto, String userId);

}
