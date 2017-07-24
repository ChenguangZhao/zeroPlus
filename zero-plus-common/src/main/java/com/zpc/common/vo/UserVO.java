package com.zpc.common.vo;

import java.util.List;

/**
 * @Title:User实体类
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/20
 */
public class UserVO {

    private Long id;

    private String userId;

    private String username;

    private String portraitUrl;

    private List<ProductsVO> productsVOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProductsVO> getProductsVOS() {
        return productsVOS;
    }

    public void setProductsVOS(List<ProductsVO> productsVOS) {
        this.productsVOS = productsVOS;
    }

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    @Override
    public String toString() {
        return "UserVO{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", username='" + username + '\'' +
            ", portraitUrl='" + portraitUrl + '\'' +
            ", productsVOS=" + productsVOS +
            '}';
    }
}
