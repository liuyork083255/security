package liu.york.config;

import liu.york.service.SecurityOauth2Auth3ClientDetailsService;
import liu.york.service.SecurityOauth2Auth3UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.InMemoryApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.Arrays;


@Configuration
@EnableAuthorizationServer
public class SecurityOauth2Auth3AuthenticationConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
    private TokenEnhancer tokenEnhancer = new TokenEnhancerChain();

//    private TokenGranter tokenGranter = new ResourceOwnerPasswordTokenGranter();

    @Autowired
    private SecurityOauth2Auth3ClientDetailsService securityOauth2Auth3ClientDetailsService;

    @Autowired
    private SecurityOauth2Auth3UserDetailsService securityOauth2Auth3UserDetailsService;

    /**
     * 授权管理对象，用于管理provider
     */
//    @Autowired
    private AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();

    /**
     * check token端点过滤器配置
     */
//    @Autowired
    private ClientCredentialsTokenEndpointFilter checkTokenEndpointFilter = new ClientCredentialsTokenEndpointFilter();

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置client详情服务对象
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(securityOauth2Auth3ClientDetailsService);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    /**
     * 配置权限管理，oauth不需要认证，token检查需要授权
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients()
                .addTokenEndpointAuthenticationFilter(checkTokenEndpointFilter);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        // 将自定义的Token增强器添加到链中
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer));

        endpoints
//                .tokenGranter()
//                .tokenStore(redisTokenStore)
                .tokenEnhancer(enhancerChain)
//                .authenticationManager(this.authenticationManager)
                .userDetailsService(this.securityOauth2Auth3UserDetailsService);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public ClientDetailsService clientDetails() {
        return new InMemoryClientDetailsService();
    }

    @Bean
    public ApprovalStore approvalStore(){
        return new InMemoryApprovalStore();
    }
}