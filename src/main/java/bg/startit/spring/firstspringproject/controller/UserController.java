package bg.startit.spring.firstspringproject.controller;

import bg.startit.spring.firstspringproject.dto.UserResponse;
import bg.startit.spring.firstspringproject.model.User;
import bg.startit.spring.firstspringproject.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  private UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserResponse> listUsers(
      @RequestParam(name = "page", required = false, defaultValue = "0") int pageNumber,
      @RequestParam(name = "size", required = false, defaultValue = "20") int pageSize) {

    Page<User> users = userService.listUsers(PageRequest.of(pageNumber, pageSize));

    return users.stream().map(user -> new UserResponse()
        .setId(user.getId())
        .setUsername(user.getUsername())
        .setLastLoginTime(user.getLastLoginTime())
        .setRegistrationTime(user.getRegistrationTime())
    ).collect(Collectors.toList());

  }


}
