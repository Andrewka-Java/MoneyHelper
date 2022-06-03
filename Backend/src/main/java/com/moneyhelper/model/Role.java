/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.model;

public enum Role {

    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    USER("USER"),
    ANONYMOUS("ANONYMOUS");

    private final String roleName;

    Role(final String roleName) {
        this.roleName = roleName;
    }


    public String getRoleName() {
        return roleName;
    }

    public static String getRoleHierarchy() {
        return "ROLE_ADMIN > ROLE_MANAGER \n ROLE_MANAGER > ROLE_USER \n ROLE_USER > ROLE_ANONYMOUS";
    }

}
