package com.example.dits.aspects;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(public String com.example.dits.controllers.TestPageController.goTest(..))")
    public void goTest(){}

    @Pointcut("execution(public String com.example.dits.controllers.TestPageController.nextTestPage(..))")
    public void nextTestPage(){}

    @Pointcut("execution(public String com.example.dits.controllers.TestPageController.testStatistic(..))")
    public void testStatistic(){}
}