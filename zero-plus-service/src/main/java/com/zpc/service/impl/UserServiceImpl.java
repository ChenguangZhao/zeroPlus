package com.zpc.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zpc.common.dataobject.UserDO;
import com.zpc.common.exception.ServiceException;
import com.zpc.common.vo.ProductsVO;
import com.zpc.common.vo.UserVO;
import com.zpc.dal.UserDao;
import com.zpc.service.ProductService;
import com.zpc.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    ProductService productService;

    /**
     * @return
     */
    @Override
    public List<UserVO> queryUser() {
        List<UserDO> userDOS = userDao.getAllUser();
        List<UserVO> userVOS = new ArrayList<>();
        for (UserDO userDO : userDOS) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDO, userVO);
            List<ProductsVO> productsVOS = productService.queryProductsByUserId(userVO.getUserId());
            userVO.setProductsVOS(productsVOS);
            userVOS.add(userVO);
        }
        return userVOS;
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public UserVO queryUserByUserId(String userId) {
        UserDO condition = new UserDO();
        condition.setUserId(userId);
        condition.setIsDeleted(0);
        UserDO userDO = userDao.selectOne(condition);
        if (userDO == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return userVO;
    }

    /**
     * @param userVO
     */
    @Override
    public void save(UserVO userVO) throws ServiceException {
        if (userVO == null) {
            throw new ServiceException("user vo is null");
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userVO, userDO);

        UserDO condition = new UserDO();
        condition.setUserId(userDO.getUserId());
        condition.setIsDeleted(0);
        UserDO existUserDO = userDao.selectOne(condition);

        if (existUserDO != null) {
            userDao.update(userDO);
        } else {
            userDO.setJoinTime(new Date(System.currentTimeMillis()));
            userDao.insertUseGeneratedKeys(userDO);
        }
    }

    /**
     * 模糊查询
     *
     * @param key
     * @return
     */
    @Override
    public List<UserVO> searchUser(String key) {
        List<UserDO> userDOS = userDao.search(key);
        List<UserVO> userVOS = new ArrayList<>();
        for (UserDO userDO : userDOS) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userDO, userVO);
            userVOS.add(userVO);
        }
        return userVOS;
    }
}
