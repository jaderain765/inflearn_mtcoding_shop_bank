package shap.mtcoding.bank.config;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import shap.mtcoding.bank.domain.user.UserEnum;

@Configuration
public class SecurityConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // JWT 서버를 만들 예정이다. Session을 사용하지 않는다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // iframe 사용을 금지 (보안상의 이유로 차단한다.)
                .headers((headerConfig) -> {
                    headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable);
                })
                // enable이면 post맨 작동안한다.
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfigurer -> corsConfigurer.configurationSource(configurationSource()))
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorizeRequest ->
                        authorizeRequest
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/api/s")
                                ).authenticated()
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/api/admin/**")
                                ).hasRole("" + UserEnum.ADMIN)
                                .anyRequest().permitAll());

        return http.build();
    }

    public CorsConfigurationSource configurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // GET, POST, PUT, DELETE (Javascript 요청 허용)
        configuration.addAllowedOriginPattern("*"); // 모든 IP 주소 허용 (프론트엔드 IP만 허용)
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 요청 허용

        /**
         * 모든 IP 주소 허용은 프론트 엔드 쪽 IP만 허용해야한다.
         * 앱 같은 경우는 모든 사용자가 다 다른 언어와 IP를 사용하기에 차단할 수 없다.
         */

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
