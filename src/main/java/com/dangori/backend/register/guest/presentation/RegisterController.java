package com.dangori.backend.register.guest.presentation;

import com.dangori.backend.common.dto.ResultResponse;
import com.dangori.backend.register.exception.RegisterExceptionType;
import com.dangori.backend.register.guest.dto.CurrentTermResponse;
import com.dangori.backend.register.guest.dto.GuestRegisterRequest;
import com.dangori.backend.register.guest.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/register-term")
    public ResultResponse<List<CurrentTermResponse>> getTermList() {

        return ResultResponse.success(registerService.getCurrentTerms());
    }

    @GetMapping("/register-check-email")
    public ResultResponse<Boolean> checkEmail(@RequestParam String userEmail) {

        boolean result =  registerService.checkEmail(userEmail);

        if(result) {
            return ResultResponse.fail(RegisterExceptionType.DUPLICATE_EMAIL, true);
        }

        return ResultResponse.success(false);
    }

    @GetMapping("/register-check-number")
    public ResultResponse<Boolean> checkNumber(@RequestParam String userNumber) {

        boolean result =  registerService.checkNumber(userNumber);

        if(result) {
            return ResultResponse.fail(RegisterExceptionType.DUPLICATE_NUMBER, true);
        }

        return ResultResponse.success(false);
    }

    @PostMapping(value = "/register-account", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResultResponse<Boolean> registerAccount(
            @RequestPart("payload") GuestRegisterRequest guestRegisterRequest,
            @RequestPart(value = "profileImage", required = false) MultipartFile profileImage
    ) {

        boolean result = registerService.registerAccount(guestRegisterRequest, profileImage);
        if(!result) {
            return ResultResponse.fail(RegisterExceptionType.DUPLICATE_ACCOUNT, false);
        }

        return ResultResponse.success(true);
    }
}
