//package liu.york.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class SecurityOauth2Auth2Config extends WebSecurityConfigurerAdapter {
//
//    /**
//     * 不注入这个 加密器 验证会报错
//     * @return
//     */
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    /**
//     * 启动加载 用户 到内存中，便于测试，这个时候就不需要实现 UserDetails 接口了
//     * @param auth
//     * @throws Exception
//     */
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        // TODO: 2018/6/26  此处有疑惑 security model
////        String password = passwordEncoder().encode("123456");
////        auth.inMemoryAuthentication().withUser("admin").password(password).roles("ADMIN")
////                .and().withUser("test").password(password).roles("USER");
////    }
//
//    /**
//     * 配置 http 安全链
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//    }
//}