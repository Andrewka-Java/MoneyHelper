/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.repository;

import com.moneyhelper.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {
}
