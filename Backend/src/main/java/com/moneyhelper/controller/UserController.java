/*
 *   Developed by Andrei Muryn© 2022
 */

package com.moneyhelper.controller;

import com.moneyhelper.dto.UserDto;
import com.moneyhelper.repository.UserRepository;
import com.moneyhelper.security.annotation.AnonymousRole;
import com.moneyhelper.security.annotation.ManagerRole;
import com.moneyhelper.security.annotation.UserRole;
import com.moneyhelper.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@ManagerRole
@RestController
@RequestMapping("/users")
class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    UserController(
            final UserRepository userRepository,
            final UserService userService
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @AnonymousRole
    @PostMapping
    ResponseEntity<Void> createUser(@Valid @RequestBody UserDto userDto) {
        userService.save(userDto);
        return status(CREATED).build();
    }

    @PutMapping("/{userId}")
    ResponseEntity<Void> editUser(
            @PathVariable String userId,
            @Valid @RequestBody UserDto userDto
    ) {
        userService.edit(userDto, userId);
        return status(ACCEPTED).build();
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userRepository.deleteById(userId);
        return ok().build();
    }

    @UserRole
    @PostMapping("/{userId}")
    ResponseEntity<Void> addCash(
            @PathVariable String userId,
            @RequestParam BigDecimal cash
    ) {
        userRepository.addCash(cash, userId);
        return status(ACCEPTED).build();
    }

}
