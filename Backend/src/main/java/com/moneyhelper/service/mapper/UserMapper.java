/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.service.mapper;

import com.moneyhelper.dto.UserDto;
import com.moneyhelper.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface UserMapper {

    String ROLE_DEFAULT = "USER";

    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "authes", ignore = true),
            @Mapping(target = "items", ignore = true),

            @Mapping(target = "role", constant = ROLE_DEFAULT)
    })
    User toUser(UserDto userDto);

}
