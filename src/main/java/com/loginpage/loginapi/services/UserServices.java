package com.loginpage.loginapi.services;

import com.loginpage.loginapi.domain.User;
import com.loginpage.loginapi.exceptions.EtAuthException;

public interface UserServices {

    User validateUser(String email, String password) throws EtAuthException;

    User registerUser(String name, String address, String city, String state, String postCode, String phone,
            String cell, String email, String password) throws EtAuthException;

}
