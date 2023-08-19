package com.loginpage.loginapi.repositories;

import com.loginpage.loginapi.domain.User;

import com.loginpage.loginapi.exceptions.EtAuthException;

public interface UserRepository {

    int create(String name, String address, String city, String state,
            String postCode, String phone, String cell, String email, String password) throws EtAuthException;

    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    int getCountByEmail(String email);

    User findById(int userId);

}
