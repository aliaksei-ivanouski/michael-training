package com.example.michaeltraining.user;

import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User.UserProjection toDto(User user);
    User toEntity(User.NewUserProjection projection);
}
