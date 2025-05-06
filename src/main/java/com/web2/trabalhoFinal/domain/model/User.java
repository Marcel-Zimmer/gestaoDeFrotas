package com.web2.trabalhoFinal.domain.model;
import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public class User {
    private Name name;
    private Email email;
    private Password password;
    private boolean isSuperUser;
    private boolean isAtive;
    private String passwordRaw;

    public User(){}
    
    public User(String name, String email, String password, boolean isSuperUser, boolean isAtive) {

        this.name = new Name(name);
        this.email = new Email(email);
        this.password = new Password(password);
        this.isSuperUser = isSuperUser;
        this.isAtive = isAtive;
    }

    public User(String email, String password){
        this.email= new Email(email);
        this.passwordRaw = password;
    }

    public String getName(){
        return name.getValue();
    }

    public String getEmail(){
        return email.getValue();
    }
    
    public Password getPassword(){
        return password;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }
    
    public boolean isAtive() {
        return isAtive;
    }
    public String getPasswordRaw() {
        return passwordRaw;
    }
    
}