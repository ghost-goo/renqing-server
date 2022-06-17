package com.belong.system.handler;

import com.belong.common.core.controller.BaseController;
import com.belong.common.core.domain.entity.SysUser;
import com.belong.common.core.domain.model.LoginUser;
import com.belong.common.utils.ServletUtils;
import com.belong.system.service.BLTokenService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户信息获取
 */
public class Handler extends BaseController {

    @Autowired
    private BLTokenService tokenService;

    protected SysUser currentUser(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser != null){
            return loginUser.getUser();
        }
        return null;
    }
}
