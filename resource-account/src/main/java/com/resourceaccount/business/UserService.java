package com.resourceaccount.business;

import com.resourceaccount.domain.User;
import com.resourceaccount.dto.UserDto;
import com.resourceaccount.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    private final UserDao dao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao dao) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.dao = dao;
    }

    public void create(UserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        this.dao.create(user);
    }
}
