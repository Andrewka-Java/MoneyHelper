/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.service.mapper;

import com.moneyhelper.dto.ItemDto;
import com.moneyhelper.model.Category;
import com.moneyhelper.model.Item;
import com.moneyhelper.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper
public interface ItemMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "category", ignore = true),
            @Mapping(target = "users", ignore = true)
    })
    Item toItem(ItemDto itemDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "name", source = "itemDto.name"),
            @Mapping(target = "price", source = "itemDto.price"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "users", source = "users")
    })
    Item toItem(ItemDto itemDto, Category category, List<User> users);

}
