package com.takebook.demo.dto.mapper;

import com.takebook.demo.dto.RentDto;
import com.takebook.demo.model.Rent;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RentMapper extends GenericMapper<Rent, RentDto>{
}
