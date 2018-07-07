package liu.york.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SecurityOauth2Auth3ClientDetailsService implements ClientDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println("loadClientByClientId ====> " + clientId);
        return clientDetailsService.loadClientByClientId(clientId);
    }

    private ClientDetailsService clientDetailsService;

    @PostConstruct
    public void init() {
        String password = passwordEncoder.encode("123456");

        InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder = new InMemoryClientDetailsServiceBuilder();
        inMemoryClientDetailsServiceBuilder.
                withClient("admin")
                .resourceIds("id1")
//                .authorizedGrantTypes("password")
                .authorities("ROLE_ADMIN")
                .scopes("read", "write")
                .secret(password)
                .and()
                .withClient("user")
                .resourceIds("id2")
//                .authorizedGrantTypes("password") // "authorization_code", "implicit"
                .authorities("ROLE_USER")
                .scopes("read", "write")
                .secret(password)
                .autoApprove(true);
        try {
            clientDetailsService = inMemoryClientDetailsServiceBuilder.build();
        } catch (Exception e) {}
    }
}