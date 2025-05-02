package com.web2.trabalhoFinal.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class User {
    @Column(insertable=false, updatable=false)
    private Name name;
    @Column(insertable=false, updatable=false)
    private Email email;
    @Column(insertable=false, updatable=false)
    private Password password;
    @Column(insertable=false, updatable=false)
    private boolean isSuperUser;

    public User(){

    }
    
    public User(String name, String email, String password, boolean isSuperUser) {

        this.name = new Name(name);
        this.email = new Email(email);
        this.password = new Password(password);
        this.isSuperUser = isSuperUser;
    }

    
    public String getName(){
        return name.getValue();
    }

    public String getEmail(){
        return email.getValue();
    }
    
    public String getPassword(){
        return password.getHashedValue();
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }        

}
