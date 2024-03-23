package com.example.charityBook.dto.mapper;

import com.example.charityBook.dto.UserDto;
import com.example.charityBook.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDto> {
}
