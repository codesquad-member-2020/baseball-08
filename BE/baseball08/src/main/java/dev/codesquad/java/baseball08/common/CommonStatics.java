package dev.codesquad.java.baseball08.common;


public class CommonStatics {

    public static final String OAUTH_URL_LOCAL = "https://github.com/login/oauth/authorize?client_id=cee445a015d00ce7828f&scope=user:email";
    public static final String OAUTH_URL_SERVER = "https://github.com/login/oauth/authorize?client_id=8d92d01b11ba14d3d18f&scope=user:email";

    public static final String HEADER_ACCEPT = "Accept";
    public static final String HEADER_MEDIA_TYPE = "application/json";
    public static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    public static final String GITHUB_USER_INFO_URL = "https://api.github.com/user";

    public static final String USER_ID = "userId";
    public static final Integer EXPIRE_TIME = 60*60*6;

    public static final String HEADER_LOCATION = "Location";
    public static final String REDIRECT_URL = "http://localhost:8080/game";

    public static final String HIT = "H";
    public static final String STRIKE = "S";
    public static final String BALL = "B";
    public static final String OUT = "O";

    public static final int RANDOM_BOUND = 100;
}
