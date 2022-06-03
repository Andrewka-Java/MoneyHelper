package com.moneyhelper.service.mapper;

import com.moneyhelper.dto.ItemDto;
import com.moneyhelper.model.Category;
import com.moneyhelper.model.Item;
import com.moneyhelper.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-04T01:15:40+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_312 (Private Build)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public Item toItem(ItemDto itemDto) {
        if ( itemDto == null ) {
            return null;
        }

        Item item = new Item();

        item.setName( itemDto.getName() );
        item.setPrice( itemDto.getPrice() );

        return item;
    }

    @Override
    public Item toItem(ItemDto itemDto, Category category, User user) {
        if ( itemDto == null && category == null && user == null ) {
            return null;
        }

        Item item = new Item();

        if ( itemDto != null ) {
            item.setName( itemDto.getName() );
            item.setPrice( itemDto.getPrice() );
        }
        if ( category != null ) {
            item.setCategory( category );
        }
        if ( user != null ) {
            item.setUser( user );
        }

        return item;
    }
}
