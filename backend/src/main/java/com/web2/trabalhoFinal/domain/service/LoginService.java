package com.web2.trabalhoFinal.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.application.dto.user.LoginResponse;
import com.web2.trabalhoFinal.domain.model.User.User;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
import com.web2.trabalhoFinal.infrastructure.repository.user.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();    

    public LoginResponse loginUser(User user) throws Exception{
        UserEntity userFind = userRepository.findByEmail(user.getEmail().getValue());
        if(userFind == null){
            throw new BadCredentialsException("Usuário ou senha incorreta");
        }
        if (!passwordEncoder.matches(user.getPasswordRaw(), userFind.getPassword())) {
            throw new BadCredentialsException("Usuário ou senha incorreta");
        }
         return new LoginResponse(true, "login bem sucedido", userFind.getId(), userFind.isAdmin());
    }

}
