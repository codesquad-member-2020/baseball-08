package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.oauth.Github;
import dev.codesquad.java.baseball08.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @GetMapping("/callback")
    public void oauthCallback(@Param("code") String code) {
        Github github = loginService.requestAccessToken(code);
        logger.info("Github AccessToekn, TokenType, Scope Data : {}", github);
    }
}
