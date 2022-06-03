/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.util;

import com.moneyhelper.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static com.moneyhelper.util.Constant.ROLE_PREFIX;

public class MoneyHelperUtil {

    public static Collection<? extends GrantedAuthority> getGrantedAuthorities(final Set<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName()))
                .collect(Collectors.toSet());
    }

}
