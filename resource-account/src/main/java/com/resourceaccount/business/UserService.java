package com.resourceaccount.business;

import com.resourceaccount.domain.User;
import com.resourceaccount.dto.UserListDto;
import com.resourceaccount.dto.UserSaveDto;
import com.resourceaccount.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao dao;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao dao) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.dao = dao;
    }

    public void create(UserSaveDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        this.dao.create(user);
    }

    public List<UserListDto> getAll() {
        return dao.getAll().stream()
            .map(user -> new UserListDto(user.getName(), user.getLogin(), user.getEmail()))
            .collect(Collectors.toList());
    }
}
