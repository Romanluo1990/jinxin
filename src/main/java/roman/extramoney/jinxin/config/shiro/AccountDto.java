package roman.extramoney.jinxin.config.shiro;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import roman.extramoney.jinxin.model.Account;

import java.util.List;

@Data
public class AccountDto {

    @JsonIgnore
    private Account account;

    private List<String> permissions;

    public Long getId() {
        return account.getId();
    }

    public String getOpenId() {
        return account.getOpenId();
    }

    public String getNickName() {
        return account.getNickName();
    }

    public String getPhone() {
        return account.getPhone();
    }

    public Integer getType() {
        return account.getType();
    }

    public Integer getStatus() {
        return account.getStatus();
    }

    public String getUserName() {
        return account.getUserName();
    }
}
