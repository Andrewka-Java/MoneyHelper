/*
 *   Developed by Andrei Muryn© 2021
 */

package com.moneyhelper.service;

import com.moneyhelper.dto.ItemDto;

public interface ItemService {

    void editItem(ItemDto itemDto, String itemId);

    void saveItem(ItemDto itemDto, String userId);

}
