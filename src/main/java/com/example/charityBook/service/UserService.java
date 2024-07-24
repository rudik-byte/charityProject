package com.example.charityBook.service;

import com.example.charityBook.dto.mapper.UserMapper;
import com.example.charityBook.exception.NotFoundException;
import com.example.charityBook.model.User;
import com.example.charityBook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User create(User user) {
        if (user != null) {
            return userRepository.save(user);
        }
        throw new NotFoundException("User cannot be a 'null'");
    }

    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", id)));
    }

    public User getByName(String lastName) {
        return userRepository.findUserByLastName(lastName)
                .orElseThrow(() -> new NotFoundException(String.format("User with this name %s not found", lastName)));
    }

    public User update(long existedUserId, User newUser) {
        User existed = userRepository.findById(existedUserId)
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", existedUserId)));

        userMapper.update(existed, userMapper.toDto(newUser));
        return userRepository.save(newUser);
    }

    public void delete(long id) {
        var existedUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with this Id %s not found", id)));
        userRepository.delete(existedUser);
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
