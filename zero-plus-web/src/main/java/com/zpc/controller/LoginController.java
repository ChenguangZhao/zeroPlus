package com.zpc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.zpc.common.exception.ServiceException;
import com.zpc.common.result.AjaxResult;
import com.zpc.common.utils.HttpUtils;
import com.zpc.common.vo.UserVO;
import com.zpc.service.UserService;
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

    private final static String FACEBOOK_URL
        = "https://graph.facebook.com/debug_token?access_token=151496775419878%7C6b7c028668ef0be85032431a0ce15741"
        + "&input_token=";

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("facebookAuth.do")
    public AjaxResult queryProductById(String callback, HttpServletRequest request,
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

                        //facebook用户与系统用户
                        userVO = checkUer(userVO);

                        //session
                        HttpSession session = request.getSession();
                        Object obj = session.getAttribute("user");
                        if (obj == null) {
                            session.setAttribute("user", userVO);
                        }

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
            //新用户Facebook登录
            System.out.println("新用户Facebook登录");
            System.out.println(userVO);
            userService.save(userVO);
            return userVO;

        } else {
            System.out.println("老用户Facebook登录");
            System.out.println(userVO);
            return existUserVO;
        }
    }

    /**
     * @param callback
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("queryLoginUser.do")
    public AjaxResult queryProductById(String callback, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("user");
        if (obj != null) {
            UserVO userVO = (UserVO)obj;
            return AjaxResult.succResult(callback, userVO);
        } else {
            UserVO userVO = userService.queryUserByUserId("107535889893405");
            return AjaxResult.succResult(callback,userVO);
        }

    }

    /**
     * logout
     *
     * @param callback
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("logout.do")
    public AjaxResult logout(String callback, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return AjaxResult.succResult(callback);
    }

}
