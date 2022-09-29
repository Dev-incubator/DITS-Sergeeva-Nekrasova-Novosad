package com.example.dits.aspects;

import com.example.dits.dto.UserInfoDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("com.example.dits.aspects.Pointcuts.goTest()")
    public void beforeGoTestLoggingAdvice(JoinPoint joinPoint) {
        HttpSession session = (HttpSession) Arrays.stream(joinPoint.getArgs()).filter(arg -> arg instanceof HttpSession).collect(Collectors.toList()).get(0);
        int testId = (int) joinPoint.getArgs()[0];
        String theme = (String) joinPoint.getArgs()[1];
        UserInfoDTO user = (UserInfoDTO) session.getAttribute("user");

        String startTestLog = user.getFirstName() + " " + user.getLastName() + " started test " + theme + " number " + testId + ", time: " + new Date();
        String startFirstQuestionLog = user.getFirstName() + " " + user.getLastName() + " started 1 question" + ", time: " + new Date();

        logger.info(startTestLog);
        logger.info(startFirstQuestionLog);
    }

    @Before("com.example.dits.aspects.Pointcuts.nextTestPage()")
    public void beforeNextTestPageLoggingAdvice(JoinPoint joinPoint) {
        HttpSession session = (HttpSession) Arrays.stream(joinPoint.getArgs()).filter(arg -> arg instanceof HttpSession).collect(Collectors.toList()).get(0);
        UserInfoDTO user = (UserInfoDTO) session.getAttribute("user");
        int questionNumber = ((int) session.getAttribute("questionNumber")) + 1;

        String startNewQuestionLog = user.getFirstName() + " " + user.getLastName() + " started " + questionNumber + " question" + ", time: " + new Date();

        logger.info(startNewQuestionLog);
    }

    @Before("com.example.dits.aspects.Pointcuts.testStatistic()")
    public void beforeTestStatisticLoggingAdvice(JoinPoint joinPoint) {
        HttpSession session = (HttpSession) Arrays.stream(joinPoint.getArgs()).filter(arg -> arg instanceof HttpSession).collect(Collectors.toList()).get(0);
        UserInfoDTO user = (UserInfoDTO) session.getAttribute("user");

        String finishTestLog = user.getFirstName() + " " + user.getLastName() + " finished test!" + " Time: " + new Date();

        logger.info(finishTestLog);
    }
}