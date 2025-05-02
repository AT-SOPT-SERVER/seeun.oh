package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.user.req.UserCreateRequest;
import org.sopt.dto.user.res.UserCreateResponse;
import org.sopt.global.exception.UserNotFoundException;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

import static org.sopt.global.common.ErrorCode.USER_NOT_FOUND;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreateResponse saveUser(UserCreateRequest userCreateRequest) {
        User user = User.of(userCreateRequest.nickname());
        User savedUser = userRepository.save(user);

        return UserCreateResponse.from(savedUser);
    }

    public User getUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }
}
