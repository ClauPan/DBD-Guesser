package com.example.mvcproducts.bootstrap;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.Role;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.PlaylistService;
import com.example.mvcproducts.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
  private final UserService userService;

  public DataLoader(UserService userService) {
    this.userService = userService;
  }

  @Override
  public void run(String... args) {
    //init();
  }

  public void init() {
    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1=new User("user1",bcrypt.encode("user1"), "user1@gmail.com");
    user1.getRoles().add(Role.ROLE_USER);
    User user2=new User("user2",bcrypt.encode("user2"), "user2@gmail.com");
    user2.getRoles().add(Role.ROLE_ADMIN);
    User user3=new User("user3",bcrypt.encode("user3"), "user3@gmail.com");
    user3.getRoles().add(Role.ROLE_ADMIN);
    user3.getRoles().add(Role.ROLE_CREATOR);
    userService.save(user1);
    userService.save(user2);
    userService.save(user3);
  }
}
