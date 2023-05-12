package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Role;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.domain.UserData;
import com.example.mvcproducts.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  public void save(UserData userData) {
    User newUser = new User(userData.getUsername(), userData.getPassword(), userData.getEmail());
    User user = userRepository.findByUsername(userData.getUsername());
    if (user == null) {
      newUser.getRoles().add(Role.ROLE_USER);
      userRepository.save(newUser);
    }
  }
}