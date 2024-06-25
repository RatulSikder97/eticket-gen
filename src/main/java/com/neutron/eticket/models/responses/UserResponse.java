package com.neutron.eticket.models.responses;

import com.neutron.eticket.models.domains.User;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String password;
    private boolean status;

    public UserResponse(Long id, String name, String email, String password, boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public User get() {
        return new User(this.getId(), this.getName(), this.getEmail(), this.getPassword(), this.isStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
