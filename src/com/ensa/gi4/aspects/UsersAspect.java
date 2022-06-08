package com.ensa.gi4.aspects;

import com.ensa.gi4.datatabase.api.UserDao;
import enums.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.ensa.gi4.aspects.AspectUtils.*;

@Aspect
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersAspect {
    final UserDao userDao;


    @AfterReturning(value = "execution( * com.ensa.gi4.service.impl.UserServiceImpl.loginUser(..)) && args(username, password)", returning = "response")
    public void SignUpFailure(String username, String password, LoginResponse response) throws IOException {
        //User user = userDao.findOneByUsername(username);
        String LogFile = getLogFilePath(1);
        BufferedWriter writer = new BufferedWriter(new FileWriter(LogFile, true));
        String message = null;
        switch (response){
            case PASSWORD_INCORRECT-> message = String.format("'%s' tried to login with a wrong password", username);
            case USER_NOT_FOUND -> message = String.format("'%s' tried to login", username);
            case SUCCESS -> message = String.format("%s has logged in successfully", this.userDao.findOneByUsername(username));
            case ACCOUNT_LOCKED -> message = String.format("'%s' tried to login with a locked account", username);
        }

        writer.write(String.format(INFO_LOG_FORMAT, IP_ADDRESS, getCurrentDateTime(), "INFO", message));
        writer.close();
    }
}
