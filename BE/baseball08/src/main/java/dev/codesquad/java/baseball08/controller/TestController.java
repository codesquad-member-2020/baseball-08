package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.entity.Inning;
import dev.codesquad.java.baseball08.oauth.Github;
import dev.codesquad.java.baseball08.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/")
    public String test() {
        return "good";
    }


}
