/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.repository;

import com.moneyhelper.dto.ItemDto;
import com.moneyhelper.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    List<Item> findAllByUserId(String userId);

    /**
     * It uses NamedNativeQuery, check Item entity for an example
     */
    @Query(nativeQuery = true)
    List<ItemDto> findAllAndCompressItem(@Param("userId") String userId);

}
