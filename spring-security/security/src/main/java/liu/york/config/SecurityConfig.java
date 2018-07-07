package liu.york.config;

import liu.york.handler.SecuritySuccessHandler;
import liu.york.service.SecurityUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * 不注入这个 加密器 验证会报错
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 启动加载 用户 到内存中，便于测试，这个时候就不需要实现 UserDetails 接口了
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 不进行加密，验证不会出来，会提示 没有加密 的警告
//        String password = passwordEncoder().encode("123456");
//        /*
//         * 1 在内存中保存用户，这里设置了以后，程序启动以后就不会有默认的 user/密码了
//         * 2 创建的用户可以指定角色，有两个方法 .authorities() 和 .roles
//         *      .authorities() 默认前缀是没有 ROLE_
//         *      .roles()       默认前缀是带有 ROLE_
//         * 3 这里需要和 http 配置角色关联
//         *      antMatchers().hasRole() 和 这里配置的 roles() 对应
//         *      antMatchers().hasAuthority() 和 这里配置的 authorities() 对应
//         *      就不用管前缀问题
//         */
//        auth.inMemoryAuthentication().withUser("admin").password(password).authorities("ADMIN")
//                .and().withUser("test").password(password).authorities("USER");
//    }

    /**
     * 配置 http 安全链
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .successHandler(new SecuritySuccessHandler())
                .and()
                .authorizeRequests()
                // 不需要登录 就可访问
                .antMatchers("/not/auth").permitAll()
                /*
                 * 1 对指定路径 配置指定的角色
                 * 2 有两种 hasRole 和 hasAuthority 两种，hasRole 默认带有前缀，hasAuthority不带
                 */
                .antMatchers("/admin").hasRole("USER")
                .antMatchers("/test").hasRole("USER")
                .anyRequest()
                .authenticated();
    }
}