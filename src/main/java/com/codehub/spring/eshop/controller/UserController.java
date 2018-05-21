package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.DTO.UserDto;
import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.UserNotAuthException;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dimitris on 15/5/2018.
 */

@RestController
@RequestMapping(value = "/e-shop/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<User> getUserById(@RequestHeader("Authorization") String accessToken)
            throws EShopException {
        return ResponseEntity
                .ok()
                .body(verifyToken(accessToken));
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long userId,
                                            @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(userService.findById(userId).get());
    }

    @GetMapping(value = "/email/{userEmail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "userEmail") String userEmail,
                                               @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(userService.findByEmail(userEmail));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<User>> getAllUsers(@RequestHeader("Authorization") String accessToken)
            throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(userService.findAll());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) throws EShopException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(user));
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody User user) throws EShopException {
        User user1 = verifyToken(accessToken);
        if (!user1.getId().equals(user.getId())) {
            throw new UserNotAuthException("Unable to update user");
        }
        userService.update(user);
        return ResponseEntity
                .ok()
                .body(userService.findById(user.getId()).get());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AccessToken> login(@RequestBody UserDto userDto) throws EShopException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        return ResponseEntity
                .ok()
                .body(userService.login(email, password));
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String accessToken)
            throws EShopException {
        verifyToken(accessToken);
        userService.logout(extractAccessToken(accessToken));
        return ResponseEntity
                .ok()
                .body("Hope to see you again!");
    }


}
