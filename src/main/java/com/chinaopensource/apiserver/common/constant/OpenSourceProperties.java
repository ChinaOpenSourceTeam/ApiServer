package com.chinaopensource.apiserver.common.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = "opensource", ignoreUnknownFields = false)
public class OpenSourceProperties {

	//Jwt 配置
	 private final Jwt jwt = new Jwt();
	 
	 public Jwt getJwt() {
		return jwt;
	}

	 public static class Jwt {
		 private String header = "Authorization";
		 private String secret = "mySecret";
		 private int expiration = 30*60;
		 
		public String getHeader() {
			return header;
		}
		public void setHeader(String header) {
			this.header = header;
		}
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public int getExpiration() {
			return expiration;
		}
		public void setExpiration(int expiration) {
			this.expiration = expiration;
		}
		 
	 }
}
