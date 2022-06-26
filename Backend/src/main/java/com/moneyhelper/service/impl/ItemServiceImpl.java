/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.service.impl;

import com.moneyhelper.dto.ItemDto;
import com.moneyhelper.model.Category;
import com.moneyhelper.model.Item;
import com.moneyhelper.model.User;
import com.moneyhelper.repository.ItemRepository;
import com.moneyhelper.repository.UserRepository;
import com.moneyhelper.service.CategoryService;
import com.moneyhelper.service.ItemService;
import com.moneyhelper.service.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static java.lang.String.format;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;
    private final CategoryService categoryService;
    private final UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(
            final ItemMapper itemMapper,
            final ItemRepository itemRepository,
            final CategoryService categoryService,
            final UserRepository userRepository
    ) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }

    @Override
    public void editItem(final ItemDto itemDto, final String itemId) {
        final Item item = itemMapper.toItem(itemDto);
        item.setId(itemId);
        itemRepository.save(item);
    }

    //ToDo: Refactor
    @Override
    public void saveItem(final ItemDto itemDto, final String userId) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(format("Failed to find user with id [%s]", userId)));

        final Category category = categoryService.applyCategory(itemDto.getCategoryName());
        final Item item = itemMapper.toItem(itemDto, category, Collections.singletonList(user));
        itemRepository.save(item);
    }

}
