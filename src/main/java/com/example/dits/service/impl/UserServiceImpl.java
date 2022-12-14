package com.example.dits.service.impl;

import com.example.dits.DAO.UserRepository;
import com.example.dits.dto.UserInfoDTO;
import com.example.dits.entity.Role;
import com.example.dits.entity.User;
import com.example.dits.service.RoleService;
import com.example.dits.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, ModelMapper modelMapper, RoleService roleService) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public UserInfoDTO getUserInfoByLogin(String login) {
        return modelMapper.map(repository.getUserByLogin(login), UserInfoDTO.class);
    }

    @Override
    public User getUserByLogin(String login) {
        return repository.getUserByLogin(login);
    }

    @Override
    public List<UserInfoDTO> getAllUsers() {
        return repository.findAll().stream().map(f -> modelMapper.map(f, UserInfoDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void save(UserInfoDTO userInfoDTO) {
        Role role = roleService.getRoleByRoleName(userInfoDTO.getRole());
        User user = User.builder()
                .firstName(userInfoDTO.getFirstName())
                .lastName(userInfoDTO.getLastName())
                .role(role)
                .login(userInfoDTO.getLogin())
                .password(passwordEncoder.encode(userInfoDTO.getPassword()))
                .build();
        repository.save(user);
    }

    @Transactional
    @Override
    public void update(UserInfoDTO userInfoDTO, int id) {
        User editUser = repository.getById(id);
        Role role = roleService.getRoleByRoleName(userInfoDTO.getRole());
        editUser.setFirstName(userInfoDTO.getFirstName());
        editUser.setLastName(userInfoDTO.getLastName());
        editUser.setRole(role);
        editUser.setLogin(userInfoDTO.getLogin());
        editUser.setPassword(passwordEncoder.encode(userInfoDTO.getPassword()));
    }

    @Transactional
    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }
}
