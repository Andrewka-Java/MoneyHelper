/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.service;

import com.moneyhelper.dto.ItemDto;

public interface ItemService {

    void editItem(ItemDto itemDto, String itemId);

    void saveItem(ItemDto itemDto, String userId);

}
