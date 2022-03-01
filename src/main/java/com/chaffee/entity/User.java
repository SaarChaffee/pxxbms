package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;


public class User implements Serializable {
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 用户编码
   */
  private String userCode;
  /**
   * 用户名称
   */
  private String username;
  /**
   * 用户密码
   */
  private String userPassword;
  /**
   * 性别（1:女、 2:男）
   */
  private Integer gender;
  /**
   * 出生日期
   */
  private Date birthday;
  /**
   * 手机
   */
  private String phone;
  /**
   * 地址
   */
  private String address;
  /**
   * 用户角色（取自角色表-角色id）
   */
  private Integer userRole;
  /**
   * 创建者（userId）
   */
  private Integer createdBy;
  /**
   * 创建时间
   */
  private Date creationDate;
  /**
   * 更新者（userId）
   */
  private Integer modifyBy;
  /**
   * 更新时间
   */
  private Date modifyDate;
  
  public User() {
  }
  
  public User( Integer id, String userCode, String username, String userPassword, Integer gender, Date birthday,
               String phone, String address, Integer userRole, Integer createdBy, Date creationDate, Integer modifyBy
      , Date modifyDate ) {
    this.id = id;
    this.userCode = userCode;
    this.username = username;
    this.userPassword = userPassword;
    this.gender = gender;
    this.birthday = birthday;
    this.phone = phone;
    this.address = address;
    this.userRole = userRole;
    this.createdBy = createdBy;
    this.creationDate = creationDate;
    this.modifyBy = modifyBy;
    this.modifyDate = modifyDate;
  }
  
  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userCode='" + userCode + '\'' +
        ", username='" + username + '\'' +
        ", userPassword='" + userPassword + '\'' +
        ", gender=" + gender +
        ", birthday=" + birthday +
        ", phone='" + phone + '\'' +
        ", address='" + address + '\'' +
        ", userRole=" + userRole +
        ", createdBy=" + createdBy +
        ", creationDate=" + creationDate +
        ", modifyBy=" + modifyBy +
        ", modifyDate=" + modifyDate +
        '}';
  }
}

