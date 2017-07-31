package com.zpc.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zpc.common.vo.UserVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/30
 */
public interface LoginService {

    /**
     * @param request
     * @return
     */
    UserVO queryLoginUser(HttpServletRequest request) throws Exception;

    /**
     * @param userVO
     * @param response
     */
    void saveCookie(UserVO userVO, HttpServletResponse response) throws Exception;
}
