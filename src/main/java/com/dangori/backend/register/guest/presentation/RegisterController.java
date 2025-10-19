package com.dangori.backend.register.guest.presentation;

import com.dangori.backend.common.dto.ResultModel;
import com.dangori.backend.register.guest.dto.CurrentTermResponse;
import com.dangori.backend.register.guest.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/guest")
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/register-term")
    public ResultModel<List<CurrentTermResponse>> getTermList() {

        return ResultModel.success(registerService.getCurrentTerms());
    }

    @PostMapping("/register-check-email")
    public ResultModel<Boolean> checkEmail(@RequestBody String userEmail) {

        return ResultModel.success(registerService.checkEmail(userEmail));
    }
}
