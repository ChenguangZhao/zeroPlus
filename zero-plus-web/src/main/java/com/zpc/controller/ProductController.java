package com.zpc.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.zpc.common.result.AjaxResult;
import com.zpc.common.vo.ProductsVO;
import com.zpc.common.vo.UserVO;
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

    private final static String PATH
        = "/Users/chenguang.zcg/IdeaProjects/zeroPlus/zero-plus-web/src/main/webapp/image/";

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

            if (!multipartFile.isEmpty()) {
                String fileName = multipartFile.getOriginalFilename();
                fileName = System.currentTimeMillis() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
                String filePath = PATH
                    + fileName;

                File file = new File(filePath);

                multipartFile.transferTo(file);
                return AjaxResult.succResult(callback, "/image/" + fileName);
            }
            return AjaxResult.errResult(callback, "上传失败");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 删除图片
     *
     * @param callback
     * @param url
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteProductsImage.do")
    public AjaxResult deleteProductsImage(String callback, String url) {
        try {
            String fileName = PATH + url.replace("/image/", "");

            File file = new File(fileName);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
            return AjaxResult.succResult(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }

    /**
     * 新增产品
     *
     * @param callback
     * @param productsVO
     * @return
     */
    @ResponseBody
    @RequestMapping("/addProducts.do")
    public AjaxResult addProducts(String callback, ProductsVO productsVO,
                                  @RequestParam("imageUrlArray") String imageUrl) {
        try {
            List<String> imageUrlList = Arrays.asList(imageUrl.split(","));
            System.out.println(imageUrlList);
            productsVO.setPictureUrl(imageUrlList);
            System.out.println(productsVO);

            return AjaxResult.succResult(callback);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.errResult(callback, e.getMessage());
        }
    }
}
