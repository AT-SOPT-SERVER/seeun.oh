package org.sopt.service;

import org.sopt.domain.User;
import org.sopt.dto.req.UserCreateRequest;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest userCreateRequest) {
        User user = new User(userCreateRequest.name());
        userRepository.save(user);
    }
}
