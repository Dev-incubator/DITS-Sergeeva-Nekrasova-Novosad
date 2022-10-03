package com.example.dits.aspects;

import com.example.dits.dto.TestInfoDTO;
import com.example.dits.dto.TopicDTO;
import com.example.dits.dto.UserInfoDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;


@Aspect
@Component
public class AdminLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(AdminLoggingAspect.class);

    @Pointcut("execution(* com.example.dits.controllers.AdminUserController.addUser(..))")
    private void addUser() {
    }

    @Pointcut("execution(* com.example.dits.controllers.AdminTestController.addTopic(..))")
    private void addTopic() {
    }

    @Pointcut("execution(* com.example.dits.controllers.AdminTestController.addTest(..))")
    private void addTest() {
    }

    @After(value = "addUser()")
    public void loggingAfterAddUser(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        UserInfoDTO user = (UserInfoDTO) args[0];
        String message = "New user (" + user.getLogin() + ") was created, time: " + new Date();
        logger.info(message);
    }

    @After(value = "addTopic()")
    public void loggingAfterAddTopic(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        TopicDTO topic = (TopicDTO) args[0];
        String message = "New topic (" + topic.getTopicName() + ") was created, time: " + new Date();
        logger.info(message);
    }

    @After(value = "addTest()")
    public void loggingAfterAddTest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        TestInfoDTO test = (TestInfoDTO) args[0];
        String message = "New test (" + test.getName() + ") was created, time: " + new Date();
        logger.info(message);
    }
}
