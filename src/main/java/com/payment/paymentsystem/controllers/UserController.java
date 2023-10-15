package com.payment.paymentsystem.controllers;

import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.CSVHelper;
import com.payment.paymentsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;


    @PostMapping(path = "/import")
    public Collection<UserDTO> create(@RequestParam("file") MultipartFile file) throws IOException {
        return userService.insertUsers(CSVHelper.csvToUsers(file.getInputStream()));
    }
}
