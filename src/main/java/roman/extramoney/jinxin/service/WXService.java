package roman.extramoney.jinxin.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.model.Account;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信小程序服务
 */
@Service
@ConfigurationProperties(prefix = "wx.app")
public class WXService {

    private static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    @Resource
    private AccountService accountService;

    private RestTemplate restTemplate;

    private String appid;

    private String secret;

    public WXService() {
        this.restTemplate = new RestTemplateBuilder().build();
    }

    public void login(String code){
        Map<String, String> responseMap = wxLogin(code);
        String openId = responseMap.get("openid");
        Account account = accountService.getByOpenId(openId);
        if(account == null)
            throw new JInXinRunTimeException(405, "未知用户");
    }

    private Map<String, String> wxLogin(String code) {
        String url = String.format(LOGIN_URL, appid, secret, code);
        Map<String, String> responseMap = restTemplate.getForObject(url,Map.class);
        String errcode = responseMap.get("errcode");
        if(StringUtils.isNotEmpty(errcode)){
            throw new JInXinRunTimeException(NumberUtils.toInt(errcode),responseMap.get("errmsg"));
        }
        return responseMap;
    }

    public void register(String code,String nickName){
        Map<String, String> responseMap = wxLogin(code);
        String openId = responseMap.get("openid");
        accountService.register(openId,nickName);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
