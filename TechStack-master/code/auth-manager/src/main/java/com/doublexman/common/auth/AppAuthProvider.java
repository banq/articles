package com.doublexman.common.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppAuthProvider extends TokenProvider {

    private static final String AuthTenantKey = "AUTH_TENANT";

    private static final String DefaultTenant = "ALL";

    public AppAuthProvider(String secretKey, long tokenValidityInMilliseconds){
        super(secretKey, tokenValidityInMilliseconds);
    }

    public String createAppAccessToken(String appId, String tenant) {
        StringBuffer sb = new StringBuffer();
        if (null == tenant || tenant.isEmpty()) {
            tenant = DefaultTenant;
        }
        HashMap<String, String> info = new HashMap<>();
        info.put(AuthTenantKey, tenant);
        return this.createToken(appId, info);
    }

    public Authentication getAuthentication(String token) {
        if (this.validateToken(token)){
            TokenProvider.ClaimParser claimParser = this.parse(token);
            String appId = claimParser.getSubject();
            String tenant = claimParser.getValue(AuthTenantKey);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(tenant));
            return new UsernamePasswordAuthenticationToken(appId, token, authorities);
        } else {
            return null;
        }
    }

}
