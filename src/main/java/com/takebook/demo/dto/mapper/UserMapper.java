package com.takebook.demo.dto.mapper;

import com.takebook.demo.dto.UserDto;
import com.takebook.demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<User, UserDto>{
}
