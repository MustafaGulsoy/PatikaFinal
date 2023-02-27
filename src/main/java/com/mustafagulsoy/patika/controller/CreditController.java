package com.mustafagulsoy.patika.controller;

import com.mustafagulsoy.patika.entity.User;
import com.mustafagulsoy.patika.services.credit.CreditService;
import com.mustafagulsoy.patika.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CreditController {

    UserService userService;
    CreditService creditService;

    @PostMapping("credit/checkUserRate")
    public ResponseEntity<Boolean> checkUserRate(@RequestBody User user) {

        return ResponseEntity.ok().body(creditService.checkCostumer(user));

    }
}
