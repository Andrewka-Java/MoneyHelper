/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.repository;

import com.moneyhelper.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Optional<Category> findByNameIgnoreCase(String categoryName);

}
