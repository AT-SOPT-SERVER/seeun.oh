package org.sopt.controller;

import org.sopt.dto.user.req.UserCreateRequest;
import org.sopt.dto.user.res.UserCreateResponse;
import org.sopt.global.common.ApiResponse;
import org.sopt.global.common.SuccessCode;
import org.sopt.service.UserService;
import org.sopt.validation.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(
            @RequestBody UserCreateRequest userCreateRequest
    ) {
        UserValidator.validateUsernameLength(userCreateRequest.nickname());
        UserCreateResponse userCreateResponse = userService.saveUser(userCreateRequest);

        return ResponseEntity.status(SuccessCode.USER_CREATED.getStatus())
                .body(ApiResponse.success(SuccessCode.USER_CREATED, userCreateResponse));
    }
}
