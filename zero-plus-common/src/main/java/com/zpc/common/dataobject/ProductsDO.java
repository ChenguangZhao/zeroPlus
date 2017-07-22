package com.zpc.common.dataobject;

import com.zpc.common.vo.ProductsVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
public class ProductsDO extends ProductsVO {

    private Integer isDeteled;

    public Integer getIsDeteled() {
        return isDeteled;
    }

    public void setIsDeteled(Integer isDeteled) {
        this.isDeteled = isDeteled;
    }

    @Override
    public String toString() {
        return "ProductsDO{" +
            "isDeteled=" + isDeteled +
            '}';
    }
}
