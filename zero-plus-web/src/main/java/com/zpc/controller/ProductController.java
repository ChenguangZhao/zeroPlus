package com.zpc.controller;

import javax.servlet.http.HttpServletResponse;

import com.zpc.common.result.AjaxResult;
import com.zpc.common.vo.ProductsVO;
import com.zpc.service.ProductService;
import com.zpc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * @return
     */
    @RequestMapping("queryProductById.do")
    public AjaxResult queryProductById(String callback, @RequestParam(value = "id") Long id) {
        try {
            ProductsVO productsVO = productService.queryProductById(id);
            return AjaxResult.succResult(callback,productsVO);
        } catch (Exception e) {
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }
}
