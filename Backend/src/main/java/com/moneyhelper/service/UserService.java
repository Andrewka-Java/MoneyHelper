/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.service;

import com.moneyhelper.dto.UserDto;
import com.moneyhelper.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void revokeRefreshToken(String userEmail, String revokedRefreshToken);

    User save(UserDto userDto);

    void edit(UserDto userDto, String userId);

}
