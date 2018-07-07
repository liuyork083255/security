package liu.york.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class SecurityOauth2AuthResourceAuth {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @EnableAuthorizationServer
    public class AuthenticationConfig{
        /*
         * 1 请求token URL http://localhost:8080/oauth/token?username=admin&password=123456&grant_type=password&scope=all
         * 2 请求类型 post 需要带两个请求头
         *     basic：Authorization Basic Y2xpZW50OnNlY3JldA== 对client 和 secret 的封装
         *     Content-Type：application/x-www-form-urlencoded
         */
        //    private static String REALM = "OAUTH_REALM";
    //
    //    @Autowired
    //    private PasswordEncoder passwordEncoder;
    ////    @Autowired
    ////    private UserDetailsService userDetailsService = new inu;
    //    //    @Autowired
    ////    private UserApprovalHandler userApprovalHandler;
    //    @Autowired
    //    private ClientDetailsService clientDetailsService;
    //    @Autowired
    //    private AuthenticationManager authenticationManager;
    //
    //    @Bean
    //    public AuthorizationCodeServices authorizationCodeServices() {
    //        return new InMemoryAuthorizationCodeServices();
    //    }
    //
    //    @Override
    //    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    //        security.realm(REALM);
    //        security.passwordEncoder(passwordEncoder);
    //        security.allowFormAuthenticationForClients();
    //        security.tokenKeyAccess("permitAll()");
    //        security.checkTokenAccess("isAuthenticated()");
    //    }
    //
    //    @Override
    //    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    //        clients.withClientDetails(clientDetailsService);
    //    }
    //
    //    @Override
    //    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    //        endpoints.tokenStore(tokenStore());
    //        endpoints.authenticationManager(authenticationManager);
    //        endpoints.authorizationCodeServices(authorizationCodeServices());
    //        endpoints.approvalStore(approvalStore());
    //    }
    //
    //    @Bean
    //    public TokenStore tokenStore() {
    //        return new InMemoryTokenStore();
    //    }
    //
    //    @Bean
    //    public ClientDetailsService clientDetails() {
    //        return new InMemoryClientDetailsService();
    //    }
    //
    //    @Bean
    //    public ApprovalStore approvalStore(){
    //        return new InMemoryApprovalStore();
    //    }
    }

    /**
     * 其实加入了这个配置，security 的配置类都是可以去除的
     */
    @Configuration
    @EnableResourceServer
    public class ResourceConfig extends ResourceServerConfigurerAdapter{
        /*
         * 1 当获取到 token 后，就可以请求资源了
         * 2 请求 token URL: 见上
         * 3 请求 资源 http://localhost:8080/admin?access_token=7dd16634-900c-4d2c-9967-ea150bfdcaba
         * 4 需要注意的是：这里的配置url是token保护的，oauth2 会自动判断token 的有效性和权限范围
         *      如果这时候又配置了 security http 安全链，那么如果获取到 token 以后，拿着token 可以访问这个安全链里面的 所有资源，
         *      不管这个安全链里面配置了什么角色，比如配置了 .antMatchers("/liu").hasRole("Liu")，那么只要有token，不管这个token是什么权限，都可以访问到
         *      所以 如果采用 oauth2以后，security http 安全链完全可以不用配置
         */
        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/not/auth").permitAll()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/user").hasRole("USER");
        }
    }
}