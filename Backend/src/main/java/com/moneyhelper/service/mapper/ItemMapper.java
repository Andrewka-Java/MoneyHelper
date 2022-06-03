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

@Mapper
public interface ItemMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "category", ignore = true)
    })
    Item toItem(ItemDto itemDto);

    @Mappings({
            @Mapping(target = "id", ignore = true),

            @Mapping(target = "name", source = "itemDto.name"),
            @Mapping(target = "price", source = "itemDto.price"),
            @Mapping(target = "category", source = "category"),
            @Mapping(target = "user", source = "user")
    })
    Item toItem(ItemDto itemDto, Category category, User user);

}
