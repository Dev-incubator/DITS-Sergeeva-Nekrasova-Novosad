package com.example.dits.mapper;

import com.example.dits.dto.TestStatistic;
import com.example.dits.dto.UserStatistics;
import com.example.dits.entity.Statistic;
import com.example.dits.entity.Test;
import com.example.dits.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class StatisticMapper {
    public UserStatistics getUserStatistics(User user, List<Statistic> statisticList) {
            Map<Test, List<Statistic>> testMap = statisticList.stream()
                    .collect(Collectors.groupingBy(statistic -> statistic.getQuestion().getTest()));

            List<TestStatistic> testStatisticList = new ArrayList<>();
            for (Map.Entry<Test, List<Statistic>> testEntry : testMap.entrySet()) {
                int numberOfQuestions = (int) testEntry.getValue().stream().count();
                int numberOfRightQuestions = (int) testEntry.getValue().stream().filter(Statistic::isCorrect).count();
                int numberOfQuestionsInTest = (int) testEntry.getKey().getQuestions().stream().count();

                TestStatistic testStatistic = TestStatistic.builder()
                        .testName(testEntry.getKey().getName())
                        .count(numberOfQuestions / numberOfQuestionsInTest)
                        .avgProc((int) (((double) numberOfRightQuestions / (double) numberOfQuestions) * 100))
                        .build();

                testStatisticList.add(testStatistic);
            }

            UserStatistics userStatistics = UserStatistics.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .login(user.getLogin())
                    .testStatisticList(testStatisticList)
                    .build();

        return userStatistics;
    }
}