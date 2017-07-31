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

    private String firstName;

    private String lastName;

    private String portraitUrl;

    private String gender;

    private String email;

    private String birthday;

    private String sourceSystem;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    @Override
    public String toString() {
        return "UserVO{" +
            "id=" + id +
            ", userId='" + userId + '\'' +
            ", username='" + username + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", portraitUrl='" + portraitUrl + '\'' +
            ", gender='" + gender + '\'' +
            ", email='" + email + '\'' +
            ", birthday='" + birthday + '\'' +
            ", sourceSystem='" + sourceSystem + '\'' +
            ", productsVOS=" + productsVOS +
            '}';
    }
}
