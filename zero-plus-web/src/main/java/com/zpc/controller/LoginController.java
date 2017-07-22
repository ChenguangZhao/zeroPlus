package com.zpc.controller;

import com.alibaba.fastjson.JSONObject;

import com.zpc.common.result.AjaxResult;
import com.zpc.common.utils.HttpUtils;
import com.zpc.common.vo.ProductsVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
@RestController
@RequestMapping(value = "/innerApi/")
public class LoginController {

    private final static String FACEBOOK_URL
        = "https://graph.facebook.com/debug_token?access_token=151496775419878%7C6b7c028668ef0be85032431a0ce15741"
        + "&input_token=";

    /**
     * @return
     */
    @RequestMapping("facebookAuth.do")
    public AjaxResult queryProductById(String callback,
                                       @RequestParam(value = "name") String username,
                                       @RequestParam(value = "id") String userId,
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

                        return AjaxResult.succResult(callback);
                    }
                }
            }
            return AjaxResult.errResult(callback, "服务器异常");

        } catch (Exception e) {
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

}
