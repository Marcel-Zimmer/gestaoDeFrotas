package com.web2.trabalhoFinal.domain.model.Administrador;

import com.web2.trabalhoFinal.domain.model.User.Address;
import com.web2.trabalhoFinal.domain.model.User.Cpf;
import com.web2.trabalhoFinal.domain.model.User.Email;
import com.web2.trabalhoFinal.domain.model.User.Name;
import com.web2.trabalhoFinal.domain.model.User.Password;
import com.web2.trabalhoFinal.domain.model.User.PhoneNumber;
import com.web2.trabalhoFinal.domain.model.User.User;

public class Administrador extends User{

    public Administrador(Name name, Email email, Password password, boolean par, boolean par1, PhoneNumber phoneNumber, Address address, Cpf cpf) {
        super(name, email, password, false, true,phoneNumber, address,cpf);
    }
}
