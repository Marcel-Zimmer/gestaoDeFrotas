package com.web2.trabalhoFinal.application.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.web2.trabalhoFinal.application.dto.user.LoginResponse;
import com.web2.trabalhoFinal.application.dto.user.UserRequestDto;
import com.web2.trabalhoFinal.application.mapper.UserMapper;
import com.web2.trabalhoFinal.domain.model.User.User;
import com.web2.trabalhoFinal.domain.service.LoginService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
public class LoginController {
    private LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    } 
    
    @PostMapping("/user")
    public LoginResponse userLogin(@RequestBody UserRequestDto dto) throws Exception{
        try {
        User user = UserMapper.toDomain(dto);
        LoginResponse userLogin = loginService.loginUser(user);
        return userLogin;
        } catch (Exception e) {
            return new LoginResponse(false, e.getMessage());
        }

    }    
}
