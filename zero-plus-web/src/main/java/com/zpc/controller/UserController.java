package com.zpc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zpc.common.result.AjaxResult;
import com.zpc.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
@RestController
@RequestMapping(value = "/innerApi/")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * @return
     */
    @RequestMapping("queryUser.do")
    public AjaxResult queryUser(String callback) {
        return AjaxResult.succResult(callback,userService.queryUser());
    }
}
