/*
 *   Developed by Andrei Muryn© 2022
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

    @Query(
            nativeQuery = true,
            value = "SELECT i.id, i.name, i.price, i.category_id FROM user_item ui " +
                    "INNER JOIN item i ON ui.item_id = i.id " +
                    "WHERE ui.user_id = :userId"
    )
    List<Item> findAllByUserId(@Param("userId") String userId);

    /**
     * It uses NamedNativeQuery, check Item entity for an example
     */
    @Query(nativeQuery = true)
    List<ItemDto> findAllAndCompressItem(@Param("userId") String userId);

}
