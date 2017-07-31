package com.zpc.service;

import java.util.List;

import com.zpc.common.exception.ServiceException;
import com.zpc.common.vo.AddressVO;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/27
 */
public interface AddressService {

    /**
     * @param userId
     * @return
     */
    List<AddressVO> queryAddressByUserId(String userId);

    /**
     * @param addressVO
     */
    void saveAddress(AddressVO addressVO) throws ServiceException;

    /**
     * @param id
     */
    void deleteAddress(Long id);

    /**
     * @param userId
     * @return
     */
    AddressVO queryDefaultAddressByUserId(String userId);
}
