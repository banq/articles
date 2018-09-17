package com.doublexman.common.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserAuthProvider extends TokenProvider {

    private static final String AuthRoleKey = "AUTH_ROLE";

    private static final String DefaultRole = "USER";

    public UserAuthProvider(String secretKey, long tokenValidityInMilliseconds) {
        super(secretKey, tokenValidityInMilliseconds);
    }

    public String createUserAuthToken(String userId, List<String> roles) {
        StringBuffer sb = new StringBuffer();
        String rolesStr = DefaultRole;
        if (null != roles && 0 != roles.size()) {
            for (String role : roles) {
                sb.append(role).append(",");
            }
            rolesStr = sb.substring(0, sb.length() - 1);
        }
        HashMap<String, String> info = new HashMap<>();
        info.put(AuthRoleKey, rolesStr);
        return this.createToken(userId, info);
    }

    public Authentication getAuthentication(String token) {
        if (this.validateToken(token)) {
            TokenProvider.ClaimParser claimParser = this.parse(token);
            String userId = claimParser.getSubject();
            String rolesStr = claimParser.getValue(AuthRoleKey);
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (String role : rolesStr.split(",")) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            return new UsernamePasswordAuthenticationToken(userId, token, authorities);
        } else {
            return null;
        }
    }

}
