package com.project.shared_calender.controller;


import com.project.shared_calender.domain.user.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class MyController {

    @PostMapping("/join")
    public ResponseEntity<?> join(User user, String password) {
        return null;
    }
}
