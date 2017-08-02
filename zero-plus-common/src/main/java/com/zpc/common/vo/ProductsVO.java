package com.zpc.common.vo;

/**
 * @Title:
 * @Description:
 * @author: chenguang.zcg
 * @version:1.1.0
 * @date 2017/07/21
 */
public class ProductsVO {

    private Long id;

    private String userId;

    private String name;

    private String description;

    private String pictureUrl;

    private UserVO userVO;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public String toString() {
        return "ProductsVO{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", pictureUrl='" + pictureUrl + '\'' +
            ", userVO=" + userVO +
            '}';
    }
}
