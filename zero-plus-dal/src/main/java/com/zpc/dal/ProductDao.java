package com.zpc.dal;

import java.util.List;

import com.zpc.common.dataobject.ProductsDO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
public interface ProductDao {

    /**
     * @param userId
     * @return
     */
    List<ProductsDO> selectProductsByUserId(String userId);

    /**
     * @param id
     * @return
     */
    ProductsDO selectProductById(Long id);
}
