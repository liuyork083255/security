package liu.york.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 实现 UserDetailsService 接口，就不用内存创建用户了
 * 这里使用 @Component 注解，spring-boot 中可以自动将自定义的 detailsService 注入
 */
@Component
public class SecurityOauth2Auth3UserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("登陆用户名 ： " + username);
        String password = passwordEncoder.encode("123456");
        /*
         * 1 这里模拟从第三方获取用户信息
         * 2 设置权限的时候需要注意，AuthorityUtils 工具类对应的是 http 配置中的 hasAuthority 类型
         *      也就是说如果 http 配置使用hasRole 控制，那么这里需要添加 ROLE_ 前缀
         */
        if(StringUtils.equals(username,"admin")){
            return new User(username,password,
                    true,true,true,true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));
        }else {
            return new User(username,password,
                    true,true,true,true,
                    AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        }
    }
}