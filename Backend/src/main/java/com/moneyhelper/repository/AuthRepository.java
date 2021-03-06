/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.repository;

import com.moneyhelper.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<Auth, String> {

    List<Auth> findAllByUserId(String userId);

}
