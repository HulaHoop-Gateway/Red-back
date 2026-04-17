package com.hulahoop.redback.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/*
 * ServerConfig
 * application.yml의 hulahoop.servers 항목을 Java 객체로 바인딩하는 설정 클래스
 * 각 마이크로서비스 서버 정보(코드, URL, 포트, 헬스체크 경로)를 리스트로 관리한다
 */
@Configuration
@ConfigurationProperties(prefix = "hulahoop")
public class ServerConfig {

    // yml의 servers 하위 항목 목록을 리스트로 받는다
    private List<ServerInfo> servers;

    public List<ServerInfo> getServers() {
        return servers;
    }

    public void setServers(List<ServerInfo> servers) {
        this.servers = servers;
    }

    // yml 항목 하나하나(- brandCode, baseUrl ...)와 매핑되는 내부 클래스
    public static class ServerInfo {
        private String brandCode;  // yml의 brand-code와 매핑 (카멜케이스로 자동 변환됨)
        private String baseUrl;    // 서버 기본 주소
        private int port;          // 서버 포트 번호
        private String healthPath; // 헬스체크 엔드포인트 경로

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
