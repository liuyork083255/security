//package liu.york.config;
//
//import liu.york.service.SecurityOauth2Auth3ClientDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.provider.ClientDetailsService;
//
//@Configuration
//public class SecurityOauth2Auth3ResourceAuth {
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Configuration
//    @EnableAuthorizationServer
//    public class AuthenticationConfig extends AuthorizationServerConfigurerAdapter{
//        /*
//         * 1 请求token URL http://localhost:8080/oauth/token?username=admin&password=123456&grant_type=password&scope=all
//         * 2 请求类型 post 需要带两个请求头
//         *     basic：Authorization Basic Y2xpZW50OnNlY3JldA== 对client 和 secret 的封装
//         *     Content-Type：application/x-www-form-urlencoded
//         */
//        //    private static String REALM = "OAUTH_REALM";
//    //
//    //    @Autowired
//    //    private PasswordEncoder passwordEncoder;
////        @Autowired
////        private UserDetailsService userDetailsService;
//    //    //    @Autowired
//    ////    private UserApprovalHandler userApprovalHandler;
////        @Autowired
////        private ClientDetailsService clientDetailsService;
//    //    @Autowired
//    //    private AuthenticationManager authenticationManager;
//    //
//    //    @Bean
//    //    public AuthorizationCodeServices authorizationCodeServices() {
//    //        return new InMemoryAuthorizationCodeServices();
//    //    }
//    //
//    //    @Override
//    //    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//    //        security.realm(REALM);
//    //        security.passwordEncoder(passwordEncoder);
//    //        security.allowFormAuthenticationForClients();
//    //        security.tokenKeyAccess("permitAll()");
//    //        security.checkTokenAccess("isAuthenticated()");
//    //    }
//    //
////        @Override
////        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
////            clients.withClientDetails(clientDetailsService);
////        }
//    //
//    //    @Override
//    //    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//    //        endpoints.tokenStore(tokenStore());
//    //        endpoints.authenticationManager(authenticationManager);
//    //        endpoints.authorizationCodeServices(authorizationCodeServices());
//    //        endpoints.approvalStore(approvalStore());
//    //    }
//    //
//    //    @Bean
//    //    public TokenStore tokenStore() {
//    //        return new InMemoryTokenStore();
//    //    }
//    //
//    //    @Bean
//    //    public ClientDetailsService clientDetails() {
//    //        return new InMemoryClientDetailsService();
//    //    }
//    //
//    //    @Bean
//    //    public ApprovalStore approvalStore(){
//    //        return new InMemoryApprovalStore();
//    //    }
//    }
//
//}