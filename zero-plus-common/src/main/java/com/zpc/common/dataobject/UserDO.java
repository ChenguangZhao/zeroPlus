package com.zpc.common.dataobject;

import com.zpc.common.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
@Mapper
public class UserDO extends UserVO {

    private Integer isDeteled;

    public Integer getIsDeteled() {
        return isDeteled;
    }

    public void setIsDeteled(Integer isDeteled) {
        this.isDeteled = isDeteled;
    }
}
