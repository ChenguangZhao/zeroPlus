package com.zpc.service;

import java.util.List;

import com.zpc.common.vo.ProductsVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
public interface ProductService {

    /**
     * @param userId
     * @return
     */
    List<ProductsVO> queryProductsByUserId(String userId);

    /**
     * @param id
     * @return
     */
    ProductsVO queryProductById(Long id);
}
