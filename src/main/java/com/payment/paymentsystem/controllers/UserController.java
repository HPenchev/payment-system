package com.payment.paymentsystem.controllers;

import com.payment.paymentsystem.data.dto.UserDTO;
import com.payment.paymentsystem.CSVHelper;
import com.payment.paymentsystem.services.UserService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/import")
    public Collection<UserDTO> create(@RequestParam("file") MultipartFile file) throws IOException {
        return userService.insertUsers(CSVHelper.csvToUsers(file.getInputStream()));
    }

    @GetMapping(path = "/{email}")
    public ResponseEntity<UserDTO> create(@PathVariable("email") String email) throws IOException {
        return ResponseEntity.of(userService.getMerchantByEmail(email));
    }


}
