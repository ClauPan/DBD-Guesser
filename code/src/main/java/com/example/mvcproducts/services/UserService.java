package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.domain.UserData;

public interface UserService {
  void save(User user);
  void save(UserData userData);
}