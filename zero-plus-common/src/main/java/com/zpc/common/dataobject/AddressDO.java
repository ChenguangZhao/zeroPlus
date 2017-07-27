package com.zpc.common.dataobject;

import com.zpc.common.vo.AddressVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/27
 */
public class AddressDO extends AddressVO {

    private Integer isDeleted;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "AddressDO{" +
            "isDeleted=" + isDeleted +
            '}';
    }
}
