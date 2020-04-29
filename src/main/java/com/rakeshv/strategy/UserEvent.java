package com.rakeshv.strategy;

import com.rakeshv.models.User;
import com.rakeshv.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Slf4j
@Component("USER")
public class UserEvent implements EventType {
    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void postConstruct() {
        if (userRepository.findAll().size() == 0) {
            User user = User.builder().build();
            userRepository.save(user);
        }
    }

    @Override
    public void processEvent(String[] action) {
        try {
            this.getClass().getDeclaredMethod(action[1].toLowerCase()).invoke(this);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public User getUser() {
        return userRepository.findAll().get(0);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void login() {
        User user = getUser();
        user.setUserLogin(user.getUserLogin() + 1);
        saveUser(user);

    }

    public void logout() {
        User user = getUser();
        if (user.getUserLogin() > 0)
            user.setUserLogin(user.getUserLogin() - 1);
        saveUser(user);
    }

    public void create() {
        User user = getUser();
        user.setUserCreate(user.getUserCreate() + 1);
        saveUser(user);
    }

    public void delete() {
        User user = getUser();
        user.setUserDelete(user.getUserDelete() + 1);
        saveUser(user);
    }

    public void disable() {
        User user = getUser();
        user.setUserDisable(user.getUserDisable() + 1);
        saveUser(user);
    }

    public void enable() {
        User user = getUser();
        user.setUserEnable(user.getUserEnable() + 1);
        saveUser(user);
    }

    public void lock() {
        User user = getUser();
        user.setUserLock(user.getUserLock() + 1);
        saveUser(user);
    }

    public void update() {
        User user = getUser();
        user.setUserUpdate(user.getUserUpdate() + 1);
        saveUser(user);
    }
}
