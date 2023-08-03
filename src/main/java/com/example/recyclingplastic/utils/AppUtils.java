package com.example.recyclingplastic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.recyclingplastic.models.Agent;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import static java.time.Instant.now;

public class AppUtils {

    public static final String FIELD_MUST_NOT_BE_EMPTY = "field must not be empty";

    public static final String ID = "id";

    public static final String APP_NAME= "RecyclPAl";

    public static final String CLAIM_VALUE = "ROLES";
    public static final String AGENT_API_VALUE = "/api/v1/agent";
    public static final String CLAIMS_VALUE = "claim";

    public static final String FRONTEND_BASE_URL = "https://www.recyclpal.org";

    public static final int ONE = 1;

    public static final int ZERO = 0;

    public static final String JSON_PATCH_CONSTANT = "application/json-patch+json";

    public static final int DEFAULT_PAGE_NUMBER = 1;

    public static final int DEFAULT_PAGE_LIMIT = 10;

    public static final String EMPTY_SPACE_VALUE=" ";
    public static  final String HTML_CONTENT_VALUE = "htmlContent";
    public static final String COMPANY_EMAIL = "noreply@recyclPal.com" ;
    public static final String ACTIVATION_LINK_VALUE="Activation Link";
    public static final String PROFILE_UPDATED_SUCCESSFULLY = "Profile Update Successful";


    public static final String EMAIL_URL = "https://api.brevo.com/v3/smtp/email";
    public static final String MAIL_API_KEY = "${sendinblue.api.key}";
    public static final String LOGIN_ENDPOINT = "/api/v1/login";

    public static final String ACCESS_TOKEN_VALUE = "access_token";

    public static final String JWT_SIGNING_SECRET = "${secret-key}";

    public static final String API_SECRET_VALUE="api_secret";
    public static final String API_KEY_VALUE = "api-key";
    public static final String ACTIVATE_ACCOUNT_URL = "localhost:8080/api/v1/customer/verify %s";
    public static final String PROFILE_UPDATE_FAILED= "PROFILE_UPDATE_FAILED";
    public static final String SENDER = "sender";
    public static final String TO = "to";

    public static final String SUBJECT="subject";

    public static final String COMPANY_NAME = "RecyclPal" ;

    public static Pageable buildPageRequest(int page, int items){
        if (page <= ZERO) page = DEFAULT_PAGE_NUMBER;
        if (page <= ZERO) items = DEFAULT_PAGE_LIMIT;
        page-= ONE;
        return PageRequest.of(page, items);
    }
    public static String generateToken(Agent agent, String secret){
        return JWT.create()
                .withIssuedAt(now())
                .withExpiresAt(now().plusSeconds(200L))
                .withClaim(ID, agent.getAgentId())
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }
    public static List<String> getAuthWhiteList(){
        return List.of(
                AGENT_API_VALUE, LOGIN_ENDPOINT
        );
    }
    public static List<SimpleGrantedAuthority> getAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities
                .stream()
                .map(grantedAuthority -> new SimpleGrantedAuthority(grantedAuthority.getAuthority()))
                .toList();
    }
}
