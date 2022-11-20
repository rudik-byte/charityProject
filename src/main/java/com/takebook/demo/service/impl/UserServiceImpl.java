package com.takebook.demo.service.impl;

import com.takebook.demo.dto.mapper.UserMapper;
import com.takebook.demo.exception.NotFoundException;
import com.takebook.demo.model.User;
import com.takebook.demo.repository.UserRepository;
import com.takebook.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public User create(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        throw new NotFoundException("User cannot be a 'null'");
    }

    @Override
    public Optional<User> getById(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", id)));
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> getByName(String lastName) {
        userRepository.findUserByLastName(lastName)
                .orElseThrow(() -> new NotFoundException(String.format("User with this name %s not found", lastName)));
        return userRepository.findUserByLastName(lastName);
    }

    @Override
    public User update(User existed, User newUser) {
        userRepository.findById(existed.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", existed.getId())));
        userMapper.update(existed, userMapper.toDto(newUser));
        return userRepository.save(newUser);
    }

    @Override
    public void delete(User user) {
        userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", user.getId())));
        userRepository.delete(user);
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public boolean isBookLimitReached(User user) {
        if (user.getBooksRented() >= user.getBookLimit()) {
            user.setBookLimitReached(true);
            return true;
        } else
            return false;
    }
}
