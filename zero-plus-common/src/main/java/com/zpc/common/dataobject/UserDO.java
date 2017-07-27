package com.zpc.common.dataobject;

import com.zpc.common.vo.UserVO;
import org.springframework.stereotype.Repository;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
@Repository
public class UserDO extends UserVO {

    private Integer isDeleted;

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "UserDO{" +
            "isDeleted=" + isDeleted +
            '}';
    }
}
