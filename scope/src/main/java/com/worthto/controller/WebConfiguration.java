package com.worthto.controller;

import com.worthto.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @sees org.springframework.web.context.support.ServletContextScope
 * org.springframework.web.context.request.SessionScope
 * org.springframework.web.context.request.RequestScope
 * @author gezz
 * @description
 * @date 2020/4/30.
 */
@Configuration
@EnableWebMvc
public class WebConfiguration {

//    @RequestScope
//    @SessionScope
    @ApplicationScope
    @Bean
    public Person person() {
        Person person = new Person();
        person.setAge(21);
        person.setName("gezz");
        return person;
    }

}
