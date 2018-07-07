package liu.york.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 *
 */
@Configuration
@EnableAuthorizationServer
public class SecurityOauth2AuthenticationConfig {

        /*
         * 1 请求token URL http://localhost:8080/oauth/token?username=admin&password=123456&grant_type=password&scope=all
         * 2 请求类型 post 需要带两个请求头
         *     basic：Authorization Basic Y2xpZW50OnNlY3JldA== 对client 和 secret 的封装
         *     Content-Type：application/x-www-form-urlencoded
         */

        // 返回结果
            /*
                {
                    "access_token": "2fa7f634-7dae-4dcd-b0df-b50485ebfe6a",
                    "token_type": "bearer",
                    "refresh_token": "4d6fd687-111f-4cbb-8f30-2c3c1f3c883c",
                    "expires_in": 43174,
                    "scope": "select"
                }
             */
}