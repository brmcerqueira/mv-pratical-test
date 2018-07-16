package com.resourceaccount.presentation.controllers;

import com.resourceaccount.business.UserService;
import com.resourceaccount.dto.UserListDto;
import com.resourceaccount.dto.UserSaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(method = PUT)
    public void create(@Valid @RequestBody UserSaveDto dto) {
        this.service.create(dto);
    }

    @RequestMapping(method = GET)
    public List<UserListDto> getAll() {
        return this.service.getAll();
    }
}
