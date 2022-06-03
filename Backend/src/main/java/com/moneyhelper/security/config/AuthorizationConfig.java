/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.security.config;

import com.moneyhelper.model.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
class AuthorizationConfig {

    @Bean
    RoleHierarchy roleHierarchy() {
        final RoleHierarchyImpl roleHierarchyBean = new RoleHierarchyImpl();
        roleHierarchyBean.setHierarchy(Role.getRoleHierarchy());
        return roleHierarchyBean;
    }

}
