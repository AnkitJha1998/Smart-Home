package com.example.smarthome;

import java.io.Serializable;

public class Users implements Serializable {

    String id;
    String username;
    String password;
    String name;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }


}
