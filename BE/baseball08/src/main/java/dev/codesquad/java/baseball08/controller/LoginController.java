package dev.codesquad.java.baseball08.controller;

import dev.codesquad.java.baseball08.oauth.Github;
import dev.codesquad.java.baseball08.oauth.GithubUser;
import dev.codesquad.java.baseball08.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import static dev.codesquad.java.baseball08.common.CommonStatics.*;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @GetMapping("/callback")
    public ResponseEntity oauthCallback(@Param("code") String code, HttpServletResponse response) {
        Github github = loginService.requestAccessToken(code);
        logger.info("Github AccessToekn, TokenType, Scope Data : {}", github);
        GithubUser githubUser = loginService.requestUserInfo(github.getAccessToken());
        logger.info("Github User Id : {}", githubUser);

        Cookie cookie = new Cookie(USER_ID, githubUser.getUserId());
        cookie.setMaxAge(EXPIRE_TIME);
        response.addCookie(cookie);
        response.setHeader(HEADER_LOCATION,"localhost:8080");
        return new ResponseEntity(HttpStatus.FOUND);
    }
}
