/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.security.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@PreAuthorize("hasRole(T(com.moneyhelper.model.Role).ADMIN)")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AdminRole {
}
