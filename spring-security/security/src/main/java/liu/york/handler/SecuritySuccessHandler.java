package liu.york.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 继承 SavedRequestAwareAuthenticationSuccessHandler 类好处是 可以调用父类的 onAuthenticationSuccess
 * 这个方法就是用户访问一个 受保护的路径，在校验成功后 会重新跳转到第一次访问的受保护的 url
 */
public class SecuritySuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        System.out.println("登录成功 ...");
        super.onAuthenticationSuccess(request,response,authentication);
    }
}