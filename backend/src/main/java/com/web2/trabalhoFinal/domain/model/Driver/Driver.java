package com.web2.trabalhoFinal.domain.model.Driver;

import com.web2.trabalhoFinal.domain.model.User.Address;
import com.web2.trabalhoFinal.domain.model.User.Cpf;
import com.web2.trabalhoFinal.domain.model.User.Email;
import com.web2.trabalhoFinal.domain.model.User.Name;
import com.web2.trabalhoFinal.domain.model.User.Password;
import com.web2.trabalhoFinal.domain.model.User.PhoneNumber;
import com.web2.trabalhoFinal.domain.model.User.User;

public class Driver extends User{

    private Cnh cnh;
    private StatusDriver status;

    public Driver(Name name, Cpf cpf, Cnh cnh, PhoneNumber phoneNumber, Address address,Email email, Password password) {
        super(name, email, password, false, true,phoneNumber, address,cpf);

        this.cnh = cnh;
        this.status = StatusDriver.DISPONIVEL;
    }

    public Cnh getCnh() {
        return cnh;
    }

    public StatusDriver getStatus() {
        return status;
    }
    
    public void setStatus(StatusDriver status) {
        this.status = status;
    }
}
