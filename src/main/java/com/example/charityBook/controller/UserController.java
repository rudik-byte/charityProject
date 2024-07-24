package com.example.charityBook.controller;

import com.example.charityBook.dto.UserDto;
import com.example.charityBook.dto.mapper.UserMapper;
import com.example.charityBook.model.User;
import com.example.charityBook.service.UserService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    public User create(@RequestBody @Validated UserDto userDto) {
        return userService.create(userMapper.fromDto(userDto));
    }

    @GetMapping
    public Page<User> getAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable(name = "userId") @NotNull long id) {
        return userService.getById(id);
    }

    @GetMapping("/search")
    public User getByName(@RequestParam(name = "name") String name) {
        return userService.getByName(name);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable @NotNull long id,
                           @RequestBody @Validated UserDto newUser) {
        return userService.update(id, userMapper.fromDto(newUser));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") @NotNull long id) {
        userService.delete(id);
    }
}
