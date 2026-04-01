package com.hulahoop.redback.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * ServerConfig
 * application.yml에 기술된 외부 서버들의 목록(hulahoop.servers)을 Java 객체로 바인딩하는 설정 클래스
 */
@Configuration
@ConfigurationProperties(prefix = "hulahoop")
public class ServerConfig {

    private List<ServerInfo> servers; // yml의 하위 - 아이템 목록을 바인딩

    public List<ServerInfo> getServers() {
        return servers;
    }

    public void setServers(List<ServerInfo> servers) {
        this.servers = servers;
    }

    // 개별 서버 정보 매핑을 위한 내부 클래스
    public static class ServerInfo {
        private String brandCode; // yml의 brand-code 와 매핑 (카멜 표기 자동 치환됨)
        private String baseUrl; // base-url
        private int port; // port
        private String healthPath; // health-path

        public String getBrandCode() {
            return brandCode;
        }

        public void setBrandCode(String brandCode) {
            this.brandCode = brandCode;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getHealthPath() {
            return healthPath;
        }

        public void setHealthPath(String healthPath) {
            this.healthPath = healthPath;
        }
    }
}
