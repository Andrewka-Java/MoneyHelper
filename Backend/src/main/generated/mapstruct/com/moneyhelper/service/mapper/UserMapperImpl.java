package com.moneyhelper.service.mapper;

import com.moneyhelper.dto.UserDto;
import com.moneyhelper.model.Role;
import com.moneyhelper.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-04T01:15:40+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_312 (Private Build)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userDto.getName() );
        user.setEmail( userDto.getEmail() );
        user.setPassword( userDto.getPassword() );
        user.setCash( userDto.getCash() );

        user.setRole( Role.USER );

        return user;
    }
}
