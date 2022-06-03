/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.controller;

import com.moneyhelper.dto.ItemDto;
import com.moneyhelper.model.Item;
import com.moneyhelper.repository.ItemRepository;
import com.moneyhelper.security.annotation.UserRole;
import com.moneyhelper.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@UserRole
@RestController
@RequestMapping("/items")
class ItemController {

    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @Autowired
    ItemController(
            final ItemRepository itemRepository,
            final ItemService itemService
    ) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @GetMapping
    ResponseEntity<List<Item>> findAllItems(@RequestParam final String userId) {
        return ok(itemRepository.findAllByUserId(userId));
    }

    @GetMapping("/compress")
    ResponseEntity<List<ItemDto>> findCompressedItems(@RequestParam final String userId) {
        return ok(itemRepository.findAllAndCompressItem(userId));
    }

    @GetMapping("/{itemId}")
    ResponseEntity<Item> findItemById(@PathVariable final String itemId) {
        final Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException(format("Filed to get item with ID [%s]", itemId)));
        return ok(item);
    }

    @PostMapping
    ResponseEntity<Void> createItem(
            @RequestParam final String userId,
            @Valid @RequestBody final ItemDto itemDto
    ) {
        itemService.saveItem(itemDto, userId);
        return status(CREATED).build();
    }

    @PatchMapping("/{itemId}")
    ResponseEntity<Void> editItem(
            @PathVariable final String itemId,
            @Valid @RequestBody final ItemDto itemDto
    ) {
        itemService.editItem(itemDto, itemId);
        return status(ACCEPTED).build();
    }

    @DeleteMapping("/{itemId}")
    ResponseEntity<Void> removeItem(@PathVariable final String itemId) {
        itemRepository.deleteById(itemId);
        return ok().build();
    }

}
