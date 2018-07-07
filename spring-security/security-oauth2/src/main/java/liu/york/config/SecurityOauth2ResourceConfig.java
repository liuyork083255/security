//package liu.york.config;
//
//import liu.york.handler.SecurityOauth2SuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.token.TokenService;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
//
///**
// * 1 在原来的 security model 中加上这个类配置，同时开启 资源服务器，那么原来的 SecurityOauth2Config 配置就有问题了
// *      访问不进行校验的 /not/auth URL 都不能访问了
// * 2 同时在 过滤链中新增了一个 OAuth2AuthenticationProcessingFilter 过滤器，但是原来的 UsernamePasswordAuthenticationFilter 过滤器没有了
// *      原因是 这个两个加载有个先后顺序，谁先加载谁就有效，另一个就没有加载进去
// *      @EnableResourceServer -> @Import(ResourceServerConfiguration.class) -> order = 3
// *      WebSecurityConfigurerAdapter -> order(100)
// *      所以只加载了 OAuth2AuthenticationProcessingFilter 过滤器
// * 3 上面说的情况是两个配置类的配置和order
// *      如果只使用 @EnableResourceServer ，然后在它的 http 中配置 .formLogin()，这样就可以添加 user|pass 过滤器了，
// *      并且OAuth2AuthenticationProcessingFilter在前，UsernamePasswordAuthenticationFilter 在后面
// */
//@Order(6)
//@Configuration
//@EnableResourceServer // @EnableResourceServer 注解自动增加了一个类型为 OAuth2AuthenticationProcessingFilter 的过滤器
//public class SecurityOauth2ResourceConfig extends ResourceServerConfigurerAdapter {
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
////                .formLogin()
////                .successHandler(new SecurityOauth2SuccessHandler())
////                .and()
//                .authorizeRequests()
//                .antMatchers("/not/auth").permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/test").hasRole("USER")
//                .anyRequest()
//                .authenticated();
//    }
//
//}