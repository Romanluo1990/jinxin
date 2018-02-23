package roman.extramoney.jinxin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import roman.extramoney.jinxin.model.common.Identify;
import roman.extramoney.jinxin.service.BaseService;

public class BaseController<S extends BaseService> {

    @Autowired
    protected S service;
}
