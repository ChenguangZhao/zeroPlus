package com.zpc.dal;

import java.util.List;

import com.zpc.common.dataobject.AddressDO;
import org.springframework.stereotype.Repository;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/27
 */
@Repository
public interface AddressDao {

    /**
     * @param userId
     * @return
     */
    List<AddressDO> selectAddressByUserId(String userId);

    /**
     * @param addressDO
     */
    void update(AddressDO addressDO);

    /**
     * @param addressDO
     */
    void insert(AddressDO addressDO);
}
