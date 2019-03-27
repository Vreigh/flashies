package com.flashies.demo.domain.user.dao;

import com.flashies.demo.domain.user.model.User;

import java.util.List;

public interface UserDao {

   void insertUser();

   List<User> getUsers();
}
