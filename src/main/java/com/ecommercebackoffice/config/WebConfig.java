package com.ecommercebackoffice.config;

import com.ecommercebackoffice.auth.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor

public class WebConfig implements WebMvcConfigurer {
    // 속성
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                // 모든 요청에 적용
                .addPathPatterns("/**")

                // 로그인 전에도 접근 가능한 URL 제외
                .excludePathPatterns(
                        "/auth/login", // 로그인
                        "/admins/signUp", // 회원가입
                        "/error" // 예외(이메일, 핸드폰 형식 오류 등)
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5500",
                        "http://127.0.0.1:5500",
                        "http://localhost:63342",
                        "https://front-backoffice-jeg9017-8364s-projects.vercel.app",
                        "https://front-backoffice-khaki.vercel.app" // 마지막에 쉼표(,) 확인
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600); // 브라우저가 CORS 검사 결과를 1시간 동안 캐싱하도록 설정
    }

}