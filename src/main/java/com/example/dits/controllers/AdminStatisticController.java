package com.example.dits.controllers;

import com.example.dits.DAO.UserRepository;
import com.example.dits.dto.TestStatistic;
import com.example.dits.dto.TopicDTO;
import com.example.dits.dto.UserStatistics;
import com.example.dits.entity.Statistic;
import com.example.dits.entity.User;
import com.example.dits.mapper.StatisticMapper;
import com.example.dits.service.TopicService;
import com.example.dits.service.impl.StatisticServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminStatisticController {
    private final StatisticServiceImpl statisticService;
    private final TopicService topicService;
    private final UserRepository userRepository;
    private final StatisticMapper statisticMapper;

    @GetMapping("/adminStatistic")
    public String testStatistic(ModelMap model){
        List<TopicDTO> topicDTOList = topicService.findAll();
        model.addAttribute("topicList",topicDTOList);
        model.addAttribute("title","Statistic");
        return "admin/test-statistic";
    }

    @ResponseBody
    @GetMapping("/getTestsStatistic")
    public List<TestStatistic> getTestsStatistics(@RequestParam int id) {
        return statisticService.getListOfTestsWithStatisticsByTopic(id);
    }

    @GetMapping("/getUserStatistic")
    public String userStatistic(ModelMap model) {
        model.addAttribute("usersList", userRepository.findAll());
        return "admin/user-statistic";
    }

    @ResponseBody
    @GetMapping("/getUserTestsStatistic")
    public List<TestStatistic> getUserTestsStatistic(@RequestParam int id) {
        User user = userRepository.getById(id);
        List<Statistic> statisticList = statisticService.getStatisticsByUser(user);
        UserStatistics userStatistics = statisticMapper.getUserStatistics(user, statisticList);
        Collections.sort(userStatistics.getTestStatisticList());
        return userStatistics.getTestStatisticList();
    }

    @ResponseBody
    @GetMapping("/adminStatistic/removeStatistic/byId")
    public String removeStatisticByUserId(@RequestParam int id){
        statisticService.removeStatisticByUserId(id);
        return "success";
    }

    @GetMapping("/adminStatistic/removeStatistic/all")
    public String removeAllStatistic(){
        statisticService.deleteAll();
        return "redirect:/admin/adminStatistic";
    }
}