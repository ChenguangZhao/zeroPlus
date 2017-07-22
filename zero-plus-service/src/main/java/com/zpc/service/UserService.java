package com.zpc.service;

import java.util.List;

import com.zpc.common.vo.UserVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
public interface UserService {
    /**
     * @return
     */
    List<UserVO> queryUser();

    /**
     * @param userId
     * @return
     */
    UserVO queryUserByUserId(String userId);
}
