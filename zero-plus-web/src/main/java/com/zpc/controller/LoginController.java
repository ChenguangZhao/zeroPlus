package com.zpc.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import com.zpc.common.constants.Keys;
import com.zpc.common.exception.ServiceException;
import com.zpc.common.result.AjaxResult;
import com.zpc.common.utils.DesUtils;
import com.zpc.common.utils.HttpUtils;
import com.zpc.common.vo.UserVO;
import com.zpc.service.LoginService;
import com.zpc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
@Controller
@RequestMapping(value = "/innerApi/")
public class LoginController {

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    private final static String FACEBOOK_URL
        = "https://graph.facebook.com/debug_token?access_token=116217095690736"
        + "%7C"
        + "7544075de5587c0351183edf20bec6a9"
        + "&input_token=";

    private final static String GOOGLE_URL
        = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
    private final static String GOOGLE_TOKEN
        = "148335857553-c3igibm0rit240ojghj2cnvnlo6p2q92.apps.googleusercontent.com";

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("facebookAuth.do")
    public AjaxResult queryProductById(String callback, HttpServletRequest request,
                                       HttpServletResponse response,
                                       @RequestParam(value = "name") String username,
                                       @RequestParam(value = "id") String userId,
                                       @RequestParam(value = "gender") String gender,
                                       @RequestParam(value = "email", required = false) String email,
                                       @RequestParam(value = "first_name", required = false) String firstName,
                                       @RequestParam(value = "last_name", required = false) String lastName,
                                       @RequestParam(value = "accessToken") String accessToken) {
        try {
            String url = FACEBOOK_URL + accessToken;
            System.out.println(url);

            JSONObject result = HttpUtils.getUrl(url);
            System.out.println(result);
            JSONObject data = result.getJSONObject("data");
            if (data != null) {
                if (data.getBoolean("is_valid")) {
                    String fbUserId = data.getString("user_id");
                    if (fbUserId.equals(userId)) {
                        UserVO userVO = new UserVO();
                        userVO.setUserId(userId);
                        userVO.setUsername(username);
                        userVO.setEmail(email);
                        userVO.setGender(gender);
                        userVO.setFirstName(firstName);
                        userVO.setLastName(lastName);
                        userVO.setSourceSystem("facebook");

                        //facebook 用户与系统用户
                        userVO = checkUer(userVO);

                        //session
                        HttpSession session = request.getSession();
                        //Object obj = session.getAttribute(Keys.SESSION_KEY);
                        //if (obj == null) {
                        session.setAttribute(Keys.SESSION_KEY, userVO);
                        loginService.saveCookie(userVO, response);
                        //}

                        return AjaxResult.succResult(callback);
                    }
                }
            }
            return AjaxResult.errResult(callback, "服务器异常");

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 验证用户
     *
     * @param userVO
     */
    private UserVO checkUer(UserVO userVO) throws ServiceException {
        UserVO existUserVO = userService.queryUserByUserId(userVO.getUserId());
        if (existUserVO == null) {
            //新用户登录
            logger.info("新用户登录,{},{}", userVO.getSourceSystem(), userVO.getUserId());
            System.out.println(userVO);
            userService.save(userVO);
            return userVO;

        } else {
            logger.info("老用户登录,{},{}", userVO.getSourceSystem(), userVO.getUserId());
            System.out.println(userVO);
            return existUserVO;
        }
    }

    /**
     * google 登录验证
     *
     * @param callback
     * @param request
     * @param username
     * @param userId
     * @param gender
     * @param email
     * @param firstName
     * @param lastName
     * @return
     */
    @ResponseBody
    @RequestMapping("googleLoginAuth.do")
    public AjaxResult googleLoginAuth(String callback, HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam(value = "ig") String username,
                                      @RequestParam(value = "Eea") String userId,
                                      @RequestParam(value = "gender", required = false) String gender,
                                      @RequestParam(value = "U3", required = false) String email,
                                      @RequestParam(value = "ofa", required = false) String firstName,
                                      @RequestParam(value = "wea", required = false) String lastName,
                                      @RequestParam(value = "token") String token) {
        try {
            String url = GOOGLE_URL + token;
            System.out.println(url);
            JSONObject result = HttpUtils.getUrl(url);
            System.out.println(result);
            String aud = result.getString("aud");
            if (aud != null && GOOGLE_TOKEN.equals(aud)) {
                UserVO userVO = new UserVO();
                userVO.setUserId(userId);
                userVO.setUsername(username);
                userVO.setEmail(email);
                userVO.setGender(gender);
                userVO.setFirstName(firstName);
                userVO.setLastName(lastName);
                userVO.setSourceSystem("google");

                //google 用户与系统用户
                userVO = checkUer(userVO);

                //session
                HttpSession session = request.getSession();
                session.setAttribute(Keys.SESSION_KEY, userVO);
                loginService.saveCookie(userVO, response);

                return AjaxResult.succResult(callback);
            } else {
                return AjaxResult.errResult(callback, "非法登录");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * @param callback
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("queryLoginUser.do")
    public AjaxResult queryLoginUser(String callback, HttpServletRequest request) throws Exception {
        UserVO userVO = loginService.queryLoginUser(request);

        return AjaxResult.succResult(callback, userVO);
    }

    /**
     * logout
     *
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping("logout.do")
    public AjaxResult logout(String callback, HttpServletRequest request, HttpServletResponse response) {
        //session
        HttpSession session = request.getSession();
        session.setAttribute(Keys.SESSION_KEY, null);

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return AjaxResult.succResult(callback);

    }


}
