package com.example.michaeltraining.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<User.UserProjection> getUsers(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return page.map(userMapper::toDto);
    }

    @Override
    public User.UserProjection getUser(UUID uuid) {
        return userMapper.toDto(
                userRepository.findAllByUuid(uuid)
        );
    }

    @Override
    public void saveUser(User.NewUserProjection projection) {
        final User user = userMapper.toEntity(projection);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}
