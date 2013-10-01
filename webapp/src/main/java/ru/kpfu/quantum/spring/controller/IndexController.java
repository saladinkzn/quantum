package ru.kpfu.quantum.spring.controller;

import java.lang.String;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kpfu.quantum.spring.repository.UserRepository;


@Controller
public class IndexController {
    /**
     * С помощью DependencyInjection получает объект для работы с пользователям
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Этот метод будет обрабатывать запросы по адресу / (в нашем случае - http://localhost:8080/
     *
     * @return строка-название jsp-файла.
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
