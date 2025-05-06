package com.web2.trabalhoFinal.application.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web2.trabalhoFinal.application.dto.UserRequestDto;
import com.web2.trabalhoFinal.application.mapper.UserMapper;
import com.web2.trabalhoFinal.domain.model.User;
import com.web2.trabalhoFinal.domain.service.LoginService;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;




@RestController
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    } 
    
    @PostMapping("/user")
    public UserEntity userLogin(@RequestBody UserRequestDto dto) throws Exception {
        User user = UserMapper.toDomain(dto);
        UserEntity userLogin = loginService.loginUser(user);
        return userLogin;
    }    
}
