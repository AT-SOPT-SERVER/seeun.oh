package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.user.req.UserCreateRequest;
import org.sopt.dto.user.res.UserCreateResponse;
import org.sopt.global.exception.InvalidRequestException;
import org.sopt.global.exception.UserNotFoundException;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

import static org.sopt.global.exception.UserErrorCode.DUPLICATE_NICKNAME;
import static org.sopt.global.exception.UserErrorCode.USER_NOT_FOUND;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserCreateResponse saveUser(UserCreateRequest userCreateRequest) {
        checkDuplicateNickname(userCreateRequest.nickname());
        User user = User.of(userCreateRequest.nickname());
        User savedUser = userRepository.save(user);

        return UserCreateResponse.from(savedUser);
    }

    public User getUser(final Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
    }

    private void checkDuplicateNickname(final String nickname) {
        // 제목 중복 검증
        boolean isAlreadyExistsNickname = userRepository.existsByNickname(nickname);
        if (isAlreadyExistsNickname) {
            throw new InvalidRequestException(DUPLICATE_NICKNAME);
        }
    }
}
