package com.zpc.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import com.zpc.common.constants.Keys;
import com.zpc.common.result.AjaxResult;
import com.zpc.common.utils.DesUtils;
import com.zpc.common.vo.UserVO;
import com.zpc.service.LoginService;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/30
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * @param request
     * @return
     * @throws Exception
     */
    @Override
    public UserVO queryLoginUser(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(Keys.SESSION_KEY);
        if (obj != null) {
            UserVO userVO = (UserVO)obj;
            return userVO;
        } else {
            System.out.println("get from cookie");
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals(Keys.COOKIE_KEY)) {
                        String ciphertext = c
                            .getValue();
                        String cookieJsonStr = DesUtils
                            .decrypt(ciphertext);
                        JSONObject cookieJson = JSONObject.parseObject(cookieJsonStr);
                        UserVO userVO = new UserVO();
                        userVO = (UserVO)JSONObject.toJavaObject(cookieJson, UserVO.class);
                        session.setAttribute(Keys.SESSION_KEY, userVO);
                        return userVO;
                    }
                }
            }
            return null;
        }
    }

    /**
     * 保存session
     *
     * @param userVO
     * @param response
     * @throws Exception
     */
    @Override
    public void saveCookie(UserVO userVO, HttpServletResponse response) throws Exception {
        String ciphertext = DesUtils.encrypt(JSONObject.toJSONString(
            userVO).toString());
        Cookie cookie = new Cookie(Keys.COOKIE_KEY, ciphertext);
        cookie.setPath("/");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);
    }
}
