package roman.extramoney.jinxin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import roman.extramoney.jinxin.config.shiro.AccountDto;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.common.Identify;
import roman.extramoney.jinxin.service.BaseService;

public class BaseController<S extends BaseService> {

    @Autowired
    protected S service;

    public AccountDto getCurAccount(){
        Subject subject = SecurityUtils.getSubject();
        AccountDto accountDto = (AccountDto) subject.getPrincipal();
        return accountDto;
    }
}
