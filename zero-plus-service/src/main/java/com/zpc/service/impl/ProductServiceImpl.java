package com.zpc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zpc.common.dataobject.ProductsDO;
import com.zpc.common.vo.ProductsVO;
import com.zpc.dal.ProductDao;
import com.zpc.service.ProductService;
import com.zpc.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDao productDao;

    @Autowired
    UserService userService;

    /**
     * @param userId
     * @return
     */
    @Override
    public List<ProductsVO> queryProductsByUserId(String userId) {
        List<ProductsDO> productsDOS = productDao.selectProductsByUserId(userId);
        List<ProductsVO> productsVOS = new ArrayList<ProductsVO>();

        for (ProductsDO productsDO : productsDOS) {
            ProductsVO productsVO = new ProductsVO();
            BeanUtils.copyProperties(productsDO, productsVO);
            productsVOS.add(productsVO);
        }
        return productsVOS;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductsVO queryProductById(Long id) {
        ProductsDO productsDO = productDao.selectProductById(id);
        ProductsVO productsVO = new ProductsVO();
        BeanUtils.copyProperties(productsDO, productsVO);
        productsVO.setUserVO(userService.queryUserByUserId(productsVO.getUserId()));
        return productsVO;
    }
}
