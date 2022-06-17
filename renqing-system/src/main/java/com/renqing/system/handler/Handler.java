package com.renqing.system.handler;

import com.renqing.common.core.controller.BaseController;
import com.renqing.common.core.domain.entity.SysUser;
import com.renqing.common.core.domain.model.LoginUser;
import com.renqing.common.utils.ServletUtils;
import com.renqing.system.service.BLTokenService;
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
