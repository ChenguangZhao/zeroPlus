package com.zpc.dal;

import java.util.List;

import com.zpc.common.dataobject.UserDO;
import org.springframework.stereotype.Repository;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
@Repository
public interface UserDao {

    /**
     * @return
     */
    List<UserDO> getAllUser();

    /**
     * @param userId
     * @return
     */
    UserDO selectUserByUserId(String userId);
}
