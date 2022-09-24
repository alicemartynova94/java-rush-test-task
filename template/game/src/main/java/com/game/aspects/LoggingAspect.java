package com.game.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(public  void createPlayer())")
    public void beforeCreatePlayerAdvice (){
       //return  (((int)Math.sqrt(2500+200*experience))-50)/100;
    }

    //return 50*(level+1)*(level+2)-experience;
}
