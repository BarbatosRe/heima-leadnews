package com.heima.user.controllerv1;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dtos.LoginDto;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@Api(value = "app端登录",tags = "app端登录",description = "app端登录")
public class ApUserLoginController {

    @Autowired
    private ApUserService apUserService;

    @PostMapping("login_auth")
    public ResponseResult login(@RequestBody LoginDto loginDto){

        return apUserService.login(loginDto);
    }
}
