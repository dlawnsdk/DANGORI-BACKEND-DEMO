package com.dangori.backend.user.presentation;

import com.dangori.backend.auth.annotation.Auth;
import com.dangori.backend.user.dto.UserDetailRequest;
import com.dangori.backend.user.dto.UserInfoResponse;
import com.dangori.backend.user.service.UserService;
import com.dangori.backend.common.dto.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/me")
    public ResultResponse<UserInfoResponse> getMyInfo(@Auth Long memberId) {
        UserInfoResponse userInfoResponse = userService.getUserInfo(memberId);
        return ResultResponse.success(userInfoResponse);
    }

    @PutMapping("/detail")
    public ResultResponse<Void> updateUserDetail(
            @Auth Long memberId,
            @RequestBody UserDetailRequest request) {

        userService.updateUserDetail(memberId, request);
        return ResultResponse.success();
    }




}
