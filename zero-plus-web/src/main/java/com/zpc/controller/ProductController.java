package com.zpc.controller;

import com.zpc.common.result.AjaxResult;
import com.zpc.common.vo.ProductsVO;
import com.zpc.service.ProductService;
import com.zpc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
@Controller
@RequestMapping(value = "/innerApi/")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("queryProductById.do")
    public AjaxResult queryProductById(String callback, @RequestParam(value = "id") Long id) {
        try {
            ProductsVO productsVO = productService.queryProductById(id);
            return AjaxResult.succResult(callback, productsVO);
        } catch (Exception e) {
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 上传图片
     *
     * @param callback
     * @param multipartFile
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadProductsImage.do")
    public AjaxResult uploadProductsImage(String callback, MultipartFile multipartFile) {
        try {
            System.out.println(multipartFile.getOriginalFilename());
            return AjaxResult.succResult(callback, "/innerApi/portrait/116284275884374341304");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }
}
