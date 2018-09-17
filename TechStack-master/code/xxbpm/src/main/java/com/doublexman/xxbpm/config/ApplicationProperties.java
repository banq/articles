package com.doublexman.xxbpm.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final JWT jwt = new JWT();

    public ApplicationProperties() { }

    public JWT getJwt() {return  this.jwt; }

    public static class JWT {
        public JWT() { }

        private String secret_key = "";

        private int token_validity_in_seconds = 0;

        public String getSecret_key() {
            return secret_key;
        }

        public void setSecret_key(String secret_key) {
            this.secret_key = secret_key;
        }

        public int getToken_validity_in_seconds() {
            return token_validity_in_seconds;
        }

        public void setToken_validity_in_seconds(int token_validity_in_seconds) {
            this.token_validity_in_seconds = token_validity_in_seconds;
        }
    }
}
