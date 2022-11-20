package com.takebook.demo.controller;

import com.takebook.demo.dto.UserDto;
import com.takebook.demo.dto.mapper.UserMapper;
import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.User;
import com.takebook.demo.service.UserService;
import com.takebook.demo.validation.OnCreateValidationGroup;
import com.takebook.demo.validation.OnUpdateValidationGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PostMapping
    public User create(@RequestBody @Validated(OnCreateValidationGroup.class) UserDto userDto) {
        return userService.create(userMapper.fromDto(userDto));
    }

    @GetMapping
    public Page<User> getAll(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable(name = "userId") @NotNull long id) {
        return userService.getById(id)
                .orElseThrow(() -> new NotFoundException("User with this " + id + " not found"));
    }

    @GetMapping("/search")
    public User getByName(@RequestParam(name = "name") String name) {
        return userService.getByName(name)
                .orElseThrow(() -> new NotFoundException("User with this " + name + " not found"));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable @NotNull long id,
                           @RequestBody @Validated(OnUpdateValidationGroup.class) UserDto newUser) {
        return userService.update(getById(id), userMapper.fromDto(newUser));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") @NotNull long id) {
        userService.delete(userService.getById(id)
                .orElseThrow(() -> new NotFoundException("User with this " + id + " not found")));
    }
}
