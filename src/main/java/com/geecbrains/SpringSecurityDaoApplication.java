package com.geecbrains;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringSecurityDaoApplication {
    // Домашнее задание:
    // 1. Заменить безопасность по ролям на безопасность по правам доступа
    // 2. * Сделать как роли, так и права доступа, у каждой роли свой набор прав
    // логин для входа  user пароль 100
    // логин admin пароль 100

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDaoApplication.class, args);
    }
}
