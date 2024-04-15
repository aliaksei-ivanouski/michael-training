package com.example.michaeltraining.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUser(Long id) {
        return userMapper.toDto(
                userRepository.findAllById(id)
        );
    }

    @Override
    public void saveUser(UserDTO dto) {
        final User user = userMapper.toEntity(dto);
        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, User user) {
        if (userRepository.existsById(id)) {
            User updatedUser = userRepository.getById(id);
            updatedUser.setName(user.getName());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setAge(user.getAge());
            userRepository.save(updatedUser);
        } else {
        System.out.println("User with id " + id + " doesn't exist in DataBase");}
    }


}
