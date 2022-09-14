package com.example.dits.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserStatisticController {
    @GetMapping("/statistics")
    public String testStatistic(Model model){
        return "user/personalStatisticWithNoTests";
    }
}