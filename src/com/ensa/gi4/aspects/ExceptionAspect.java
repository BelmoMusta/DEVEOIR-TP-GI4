package com.ensa.gi4.aspects;

import com.ensa.gi4.service.api.I18nService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


import static com.ensa.gi4.aspects.AspectUtils.*;


@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ExceptionAspect {
    final I18nService i18nService;


    @AfterThrowing(value = "execution( * com.ensa.gi4.controller.*.*(..) ) || execution( * com.ensa.gi4.service.impl.*.*(..)) ", throwing = "ex")
    public void wrongInput(JoinPoint jp, Exception ex) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (ex.getClass().getSimpleName().equals("InputMismatchException"))
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println(i18nService.getText("message.wrong.input"));
        });

    }

    @AfterThrowing(value = "execution( * com..impl.*.*(..) ) || execution( * com..controller.*.*(..))", throwing = "e")
    public void registerException(JoinPoint jp, Exception e) throws IOException {
        String LogFile = getLogFilePath(2);
        BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true));
        LogLine logLine = new LogLine("ERROR", jp.getSignature().toString(), e.getClass().getSimpleName(), e.getMessage());
        writer.write(String.format(ERROR_LOG_FORMAT, logLine.getElements()));
        writer.close();
    }

}

class LogLine{
    String time;
    String type;
    String thrower;
    String exceptionClass;
    String message;

    public LogLine(String type, String thrower, String exceptionClass, String message) {
        this.type = type;
        this.time = getCurrentDateTime();
        this.thrower = thrower;
        this.exceptionClass = exceptionClass;
        this.message = message.replaceAll("\\n", "\t");
    }

    public String[] getElements(){
        return new String[]{IP_ADDRESS, time, type, thrower, exceptionClass, message};
    }
}