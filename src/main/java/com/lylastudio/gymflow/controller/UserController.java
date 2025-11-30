package com.lylastudio.gymflow.controller;

import com.lylastudio.gymflow.dto.*;
import com.lylastudio.gymflow.service.AuthService;
import com.lylastudio.gymflow.util.ApiResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;
    private final ApiResponseUtil responseUtil;

    @Operation(description = "For set user status, enable or disable")
    @PostMapping("/updateStatus")
    public ResponseEntity<ApiResponse<Void>> updateStatus(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        /**
         * Logic To be implemented Here
         * */
        return ResponseEntity.ok(responseUtil.success("api.not.implemented.yet"));
    }

    @Operation(description = "For edit user role")
    @PostMapping("/changeRole")
    public ResponseEntity<ApiResponse<Void>> changeRole(@Valid @RequestBody AuthRequest authRequest) {
        /**
         * Logic To be implemented Here
         * */
        return ResponseEntity.ok(responseUtil.success("api.not.implemented.yet"));
    }


}
