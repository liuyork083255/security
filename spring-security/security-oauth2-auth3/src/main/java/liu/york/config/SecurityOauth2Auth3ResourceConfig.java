package liu.york.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class SecurityOauth2Auth3ResourceConfig extends ResourceServerConfigurerAdapter {
    /**
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