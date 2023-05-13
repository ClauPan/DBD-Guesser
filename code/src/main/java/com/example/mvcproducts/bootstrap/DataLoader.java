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
  private final PlaylistService playlistService;

  public DataLoader(UserService userService, PlaylistService playlistService) {
    this.userService = userService;
    this.playlistService = playlistService;
  }

  @Override
  public void run(String... args) {
    PasswordEncoder bcrypt = new BCryptPasswordEncoder();
    User user1=new User("user1",bcrypt.encode("user1"), "user1@gmail.com");
    user1.getRoles().add(Role.ROLE_USER);
    User user2=new User("user2",bcrypt.encode("user2"), "user2@gmail.com");
    user2.getRoles().add(Role.ROLE_ADMIN);
    userService.save(user1);
    userService.save(user2);

    Playlist playlist1 = new Playlist("playlist1", "geo", "desc1", user2);
    Playlist playlist2 = new Playlist("playlist2", "trivia", "desc2", user2);
    Playlist playlist3 = new Playlist("playlist3", "geo", "desc3", user1);
    Playlist playlist4 = new Playlist("playlist4", "geo", "desc4", user1);
    Playlist playlist5 = new Playlist("playlist5", "geo", "desc5", user1);

    playlistService.save(playlist1);
    playlistService.save(playlist2);
    playlistService.save(playlist3);
    playlistService.save(playlist4);
    playlistService.save(playlist5);
  }
}
