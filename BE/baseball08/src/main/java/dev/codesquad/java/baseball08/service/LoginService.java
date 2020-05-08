package dev.codesquad.java.baseball08.service;

import dev.codesquad.java.baseball08.common.CommonStatics;
import dev.codesquad.java.baseball08.oauth.Github;
import dev.codesquad.java.baseball08.oauth.GithubUser;
import dev.codesquad.java.baseball08.oauth.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static dev.codesquad.java.baseball08.common.CommonStatics.*;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Value("${GITHUB_CLIENT_ID}")
    private String GITHUB_CLIENT_ID;

    @Value("${GITHUB_CLIENT_SECRET}")
    private String GITHUB_CLIENT_SECRET;

    public Github requestAccessToken(String code) {
        RequestBody requestBody = RequestBody.builder()
                .clientId(GITHUB_CLIENT_ID)
                .clientSecret(GITHUB_CLIENT_SECRET)
                .code(code)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HEADER_ACCEPT, HEADER_MEDIA_TYPE);

        HttpEntity<?> httpEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Github> responseEntity = new RestTemplate().postForEntity(GITHUB_ACCESS_TOKEN_URL, httpEntity, Github.class);
        return responseEntity.getBody();
    }

    public GithubUser requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<GithubUser> responseEntity = new RestTemplate().exchange(GITHUB_USER_INFO_URL, HttpMethod.GET, httpEntity, GithubUser.class);
        return responseEntity.getBody();
    }
}
