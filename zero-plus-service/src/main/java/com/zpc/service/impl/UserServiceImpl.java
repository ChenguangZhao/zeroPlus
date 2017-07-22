package com.zpc.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zpc.common.dataobject.UserDO;
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
        List<UserVO> userVOS = new ArrayList<UserVO>();
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
        UserDO userDO = userDao.selectUserByUserId(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return userVO;
    }
}
