package com.zpc.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zpc.common.constants.Keys;
import com.zpc.common.exception.ServiceException;
import com.zpc.common.result.AjaxResult;
import com.zpc.common.vo.UserVO;
import com.zpc.service.LoginService;
import com.zpc.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
@Controller
@RequestMapping(value = "/innerApi/")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    private final static String PATH
        = "/Users/chenguang.zcg/IdeaProjects/zeroPlus/zero-plus-web/src/main/webapp/image/";

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("queryUser.do")
    public AjaxResult queryUser(String callback) {
        return AjaxResult.succResult(callback, userService.queryUser());
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("uploadHeadPortrait.do")
    public AjaxResult uploadHeadPortrait(String callback, HttpServletRequest request,
                                         //@RequestParam(value = "userId", required = false) String userId,
                                         MultipartFile multipartFile) {
        try {

            if (!multipartFile.isEmpty()) {
                UserVO userVO = loginService.queryLoginUser(request);
                String fileName = multipartFile.getOriginalFilename();
                fileName = userVO.getUserId() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
                String filePath = PATH
                    + fileName;

                File file = new File(filePath);

                multipartFile.transferTo(file);

                userVO.setPortraitUrl(fileName);
                userService.save(userVO);
            }
            return AjaxResult.succResult(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 获取头像图片
     */
    @RequestMapping({"/portrait/{userId}"})
    public void portrait(@PathVariable String userId, HttpServletResponse response) {
        UserVO userVO = userService.queryUserByUserId(userId);
        String filePath = PATH + userVO.getPortraitUrl();
        File file = new File(filePath);

        //判断文件是否存在如果不存在就返回默认图标
        Boolean changeFile = !(file.exists() && file.canRead()) || StringUtils.isEmpty(userVO.getPortraitUrl());
        if (changeFile) {
            file = new File(PATH + "default.png");
        }

        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[(int)file.length()];
            inputStream.read(data);
            inputStream.close();

            response.setContentType("image/png");

            OutputStream stream = response.getOutputStream();
            stream.write(data);
            stream.flush();
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param callback
     * @param userVO
     * @return
     */
    @ResponseBody
    @RequestMapping("saveBaseInfo.do")
    public AjaxResult saveBaseInfo(String callback, HttpServletRequest request, HttpServletResponse response,
                                   UserVO userVO) {
        try {
            userService.save(userVO);

            //更新session和cookie
            HttpSession session = request.getSession();
            session.setAttribute(Keys.SESSION_KEY, userVO);
            loginService.saveCookie(userVO, response);

            return AjaxResult.succResult(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());

        }
    }

    /**
     * 模糊查询User
     *
     * @param callback
     * @param key
     * @return
     */
    @ResponseBody
    @RequestMapping("searchUser.do")
    public AjaxResult searchUser(String callback, String key) {
        try {
            List<UserVO> userVOList = userService.searchUser(key);
            return AjaxResult.succResult(callback, userVOList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }
}
