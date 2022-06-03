/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.repository;

import com.moneyhelper.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String name);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user usr SET usr.cash = usr.cash + :cash WHERE usr.id = :userId")
    @Transactional
    void addCash(BigDecimal cash, String userId);

}
