package com.takebook.demo.service;

import com.takebook.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);

    Optional<User> getById(long id);

    Optional<User> getByName(String name);

    User update(User existed, User newUser);

    void delete(User user);

    Page<User> getAll(Pageable pageable);

    boolean isBookLimitReached(User user);
}
