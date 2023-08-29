package com.project.shared_calender.controller;


import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.security.JwtTokenProvider;
import com.project.shared_calender.common.security.domain.TokenDto;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.entity.UserEntity;
import com.project.shared_calender.service.MyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/my")
@RequiredArgsConstructor
public class MyController {

    private final MyService myService;

    @PostMapping("/join")
    public ResponseEntity<Object> join(@RequestBody User user,
                                       @RequestBody String password) {
        Boolean isJoin = myService.join(user, password);
        return ResponseCode.SUCCESS.response(isJoin);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody String email,
                                        @RequestBody String password) {
        TokenDto token = myService.login(email, password);
        return ResponseCode.SUCCESS.response(token);
    }
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@Header(name = "refreshToken") String refreshToken){
        boolean isDropped = myService.logout(refreshToken);
        return ResponseCode.SUCCESS.response(isDropped);
    }

}
