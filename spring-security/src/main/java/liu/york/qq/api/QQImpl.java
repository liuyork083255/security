package liu.york.qq.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.client.RestTemplate;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private String appId;
    private String openId;

    private String url_get_openid = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private String url_get_userinfo = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private RestTemplate restTemplate;

    public QQImpl(String accessToken, String appId){
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(url_get_openid,accessToken);
        String result = getRestTemplate().getForObject(url,String.class);

        System.out.println(result);
         this.openId = StringUtils.substringBetween(result, "\"openid\":","}");

    }

    @Override
    public QQUserInfo getQQUserInfo() {

        String url = String.format(url_get_userinfo,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);

        System.out.println(result);

        return null;
    }
}