package com.example.dits.controllers;

import com.example.dits.entity.Statistic;
import com.example.dits.entity.User;
import com.example.dits.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserStatisticController {
    StatisticService statisticService;

    @GetMapping("/statistics")
    public String userStatistic(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Statistic> statisticList = statisticService.getStatisticsByUser(user);

        return "user/personalStatistic";
    }
}