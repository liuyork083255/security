package liu.york.config;

import liu.york.handler.LiuYorkAuthenticationFailHandler;
import liu.york.handler.LiuYorkAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LiuYorkAuthenticationFailHandler liuYorkAuthenticationFailHandler;
    @Autowired
    private LiuYorkAuthenticationSuccessHandler liuYorkAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
         * 1 先认证
         * 2 后授权
         * 3 spring-boot 默认有很多过滤器，比如 basic form 等，如果只配置了 form，那么basic 不会生效
         * 4 过滤器理解：
         *      对于form过滤器，如果直接访问一个地址，没有user password参数，那么basic是不会处理的，直接穿过
         *      但是如果带有参数，那么会进入这个过滤器中
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         *
         * FilterSecurityInterceptor
         *      主要是验证用户权限和配置的权限是否匹配
         *      也就是如果第一次登陆，输入 username password ，那么会被form过滤器拦截，然后校验，校验
         */
        http
                .httpBasic()
                .and()
            .formLogin()
//                .successHandler(liuYorkAuthenticationSuccessHandler)
//                .failureHandler(liuYorkAuthenticationFailHandler)
            .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/oauth/authorize","/json/login").permitAll()
            .anyRequest()
            .authenticated();

    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

}