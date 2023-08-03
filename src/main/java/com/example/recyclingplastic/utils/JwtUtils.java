package com.example.recyclingplastic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.recyclingplastic.exceptions.RecycleException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

import static com.example.recyclingplastic.utils.AppUtils.CLAIMS_VALUE;

@AllArgsConstructor
@Getter
public class JwtUtils {
    private final String secret;

    public Map<String, Claim> extractClaimsFrom(String token) throws RecycleException {
        validateToken(token);
        DecodedJWT decodedJWT = validateToken(token);
        if(decodedJWT.getClaim(CLAIMS_VALUE) == null) throw new RecycleException(" ");
        return decodedJWT.getClaims();
    }

    private DecodedJWT validateToken(String token){
        return JWT.require(Algorithm.HMAC512(secret))
                .build().verify(token);
    }
}
