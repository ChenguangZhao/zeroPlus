package com.zpc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zpc.common.dataobject.AddressDO;
import com.zpc.common.exception.ServiceException;
import com.zpc.common.vo.AddressVO;
import com.zpc.dal.AddressDao;
import com.zpc.service.AddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/27
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressDao addressDao;

    /**
     * @param userId
     * @return
     */
    @Override
    public List<AddressVO> queryAddressByUserId(String userId) {
        List<AddressDO> addressDOS = addressDao.selectAddressByUserId(userId);
        List<AddressVO> addressVOS = new ArrayList<AddressVO>();
        for (AddressDO addressDO : addressDOS) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressDO, addressVO);
            addressVOS.add(addressVO);
        }
        return addressVOS;
    }

    /**
     * save
     *
     * @param addressVO
     */
    @Override
    public void saveAddress(AddressVO addressVO) throws ServiceException {
        if (addressVO == null) {
            throw new ServiceException("address vo is null");
        }

        AddressDO addressDO = new AddressDO();
        BeanUtils.copyProperties(addressVO, addressDO);
        if (addressVO.getId() != null && addressVO.getId() > 0) {
            //update
            addressDao.update(addressDO);
        } else {
            AddressVO defaultAddressVO = queryDefaultAddressByUserId(addressDO.getUserId());
            if (defaultAddressVO == null) {
                addressDO.setIsDefault(1);
            } else {
                addressDO.setIsDefault(0);
            }
            addressDao.insert(addressDO);
        }
    }

    /**
     * 查询默认地址
     *
     * @param userId
     * @return
     */
    @Override
    public AddressVO queryDefaultAddressByUserId(String userId) {
        AddressDO addressDO = addressDao.selectDefaultAddressByUserId(userId);
        if (addressDO == null) {
            return null;
        } else {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressDO, addressVO);
            return addressVO;
        }
    }

    /**
     * delete
     *
     * @param id
     */
    @Override
    public void deleteAddress(Long id) {
        AddressDO addressDO = new AddressDO();
        addressDO.setIsDeleted(1);
        addressDO.setId(id);
        addressDao.update(addressDO);
    }
}
