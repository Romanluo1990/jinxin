package roman.extramoney.jinxin.config.shiro;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.HostAuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

@Data
@AllArgsConstructor
public class WxToken implements AuthenticationToken {


    private String code;


    @Override
    public Object getPrincipal() {
        return getCode();
    }

    @Override
    public Object getCredentials() {
        return getCode();
    }
}
