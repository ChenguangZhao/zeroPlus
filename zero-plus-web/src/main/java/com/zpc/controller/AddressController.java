package com.zpc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.zpc.common.result.AjaxResult;
import com.zpc.common.vo.AddressVO;
import com.zpc.common.vo.UserVO;
import com.zpc.service.AddressService;
import com.zpc.service.LoginService;
import org.apache.commons.lang.StringUtils;
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
 * @date 2017/07/27
 */
@Controller
@RequestMapping(value = "/innerApi/")
public class AddressController {

    @Autowired
    AddressService addressService;
    @Autowired
    LoginService loginService;

    /**
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping("saveAddress.do")
    public AjaxResult saveAddress(String callback, AddressVO addressVO) {
        try {
            addressService.saveAddress(addressVO);
            return AjaxResult.succResult(callback);
        } catch (Exception e) {
            return AjaxResult.errResult(callback, e.getMessage());

        }
    }

    /**
     * @param callback
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("queryAddressByUserId.do")
    public AjaxResult saveAddress(String callback,
                                  @RequestParam(value = "userId", required = false) String userId,
                                  HttpServletRequest request) {
        try {
            if (StringUtils.isEmpty(userId)) {
                UserVO userVO = loginService.queryLoginUser(request);
                if (userVO != null) {
                    userId = userVO.getUserId();
                }
            }

            List<AddressVO> addressVOS = addressService.queryAddressByUserId(userId);
            return AjaxResult.succResult(callback, addressVOS);

        } catch (Exception e) {
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * @param callback
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteAddress.do")
    public AjaxResult deleteAddress(String callback, Long id) {
        try {
            addressService.deleteAddress(id);
            return AjaxResult.succResult(callback);
        } catch (Exception e) {
            return AjaxResult.errResult(callback, e.getMessage());

        }
    }

    @ResponseBody
    @RequestMapping("settingDefaultAddress.do")
    public AjaxResult settingDefaultAddress(HttpServletRequest request, String callback, Long id) {
        try {
            UserVO userVO = loginService.queryLoginUser(request);
            if (userVO != null) {
                String userId = userVO.getUserId();
                AddressVO defaultAddressVO = addressService.queryDefaultAddressByUserId(userId);
                if (defaultAddressVO != null) {
                    defaultAddressVO.setIsDefault(0);
                    addressService.saveAddress(defaultAddressVO);
                }
                AddressVO addressVO = new AddressVO();
                addressVO.setId(id);
                addressVO.setIsDefault(1);
                addressService.saveAddress(addressVO);

            }
            return AjaxResult.succResult(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());

        }
    }
}
