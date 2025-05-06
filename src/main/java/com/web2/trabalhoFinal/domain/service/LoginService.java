package com.web2.trabalhoFinal.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.trabalhoFinal.domain.model.Password;
import com.web2.trabalhoFinal.domain.model.User;
import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
import com.web2.trabalhoFinal.infrastructure.repository.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    
    public UserEntity loginUser(User user) throws Exception{
        UserEntity userFind = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new EntityNotFoundException("Usuário ou senha incorreta"));
        
        
        if(user.getPassword() == userFind.getPassword()){
            return userFind;
        }
            return null;
        
    }

}
