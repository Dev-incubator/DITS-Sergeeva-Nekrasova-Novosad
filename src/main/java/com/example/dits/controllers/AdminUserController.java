package com.example.dits.controllers;

import com.example.dits.dto.UserInfoDTO;
import com.example.dits.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {
    private final UserService userService;

    @GetMapping("/editorUser")
    public String getUsers(ModelMap model) {
        model.addAttribute("title", "Editor user");
        return "admin/user-editor";
    }

    @ResponseBody
    @PostMapping("/addUser")
    public List<UserInfoDTO> addUser(@RequestBody UserInfoDTO userInfoDTO) {
        userService.save(userInfoDTO);
        return getUserList();
    }

    @ResponseBody
    @PutMapping("/editUser")
    public List<UserInfoDTO> editUser(@RequestBody UserInfoDTO userInfoDTO) {
        userService.update(userInfoDTO, userInfoDTO.getUserId());
        return getUserList();
    }

    @ResponseBody
    @DeleteMapping("/removeUser")
    public List<UserInfoDTO> removeUser(@RequestParam int userId) {
        userService.delete(userId);
        return getUserList();
    }

    @ResponseBody
    @GetMapping("/getUsers")
    public List<UserInfoDTO> getUserList() {
        return userService.getAllUsers();
    }
}
