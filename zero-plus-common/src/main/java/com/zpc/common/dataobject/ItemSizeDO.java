package com.zpc.common.dataobject;

import com.zpc.common.vo.ItemColorVO;
import com.zpc.common.vo.ItemSizeVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/08/12
 */
public class ItemSizeDO extends ItemSizeVO {

    private Integer isDeleted;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "ItemColorDO{" +
            "isDeleted=" + isDeleted +
            '}';
    }
}
