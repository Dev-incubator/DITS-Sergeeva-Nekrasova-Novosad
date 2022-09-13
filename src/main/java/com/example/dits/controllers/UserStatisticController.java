package com.example.dits.controllers;

import com.example.dits.dto.UserInfoDTO;
import com.example.dits.dto.UserStatistics;
import com.example.dits.entity.Statistic;
import com.example.dits.entity.User;
import com.example.dits.mapper.StatisticMapper;
import com.example.dits.mapper.UserMapper;
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
    private final StatisticService statisticService;
    private final StatisticMapper statisticMapper;
    private final UserMapper userMapper;

    @GetMapping("/statistics")
    public String userStatistic(Model model, HttpSession session){
        UserInfoDTO userInfoDTO = (UserInfoDTO) session.getAttribute("user");
        User user = userMapper.convertUserInfoDTOToUser(userInfoDTO);

        List<Statistic> statisticList = statisticService.getStatisticsByUser(user);
        UserStatistics userStatistics = statisticMapper.getUserStatistics(user, statisticList);
        session.setAttribute("userStatistics", userStatistics);
        return "user/personalStatistic";
    }
}