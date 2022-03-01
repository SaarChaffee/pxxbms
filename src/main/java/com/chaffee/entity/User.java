package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-01 15:48:57
 */
public class User implements Serializable {
  private static final long serialVersionUID = -20839496765469667L;
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 用户编码
   */
  private String usercode;
  /**
   * 用户名称
   */
  private String username;
  /**
   * 用户密码
   */
  private String userpassword;
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
  private Integer userrole;
  /**
   * 创建者（userId）
   */
  private Integer createdby;
  /**
   * 创建时间
   */
  private Date creationdate;
  /**
   * 更新者（userId）
   */
  private Integer modifyby;
  /**
   * 更新时间
   */
  private Date modifydate;
  
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
  
  public String getUsercode() {
    return usercode;
  }
  
  public void setUsercode( String usercode ) {
    this.usercode = usercode;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername( String username ) {
    this.username = username;
  }
  
  public String getUserpassword() {
    return userpassword;
  }
  
  public void setUserpassword( String userpassword ) {
    this.userpassword = userpassword;
  }
  
  public Integer getGender() {
    return gender;
  }
  
  public void setGender( Integer gender ) {
    this.gender = gender;
  }
  
  public Date getBirthday() {
    return birthday;
  }
  
  public void setBirthday( Date birthday ) {
    this.birthday = birthday;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone( String phone ) {
    this.phone = phone;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress( String address ) {
    this.address = address;
  }
  
  public Integer getUserrole() {
    return userrole;
  }
  
  public void setUserrole( Integer userrole ) {
    this.userrole = userrole;
  }
  
  public Integer getCreatedby() {
    return createdby;
  }
  
  public void setCreatedby( Integer createdby ) {
    this.createdby = createdby;
  }
  
  public Date getCreationdate() {
    return creationdate;
  }
  
  public void setCreationdate( Date creationdate ) {
    this.creationdate = creationdate;
  }
  
  public Integer getModifyby() {
    return modifyby;
  }
  
  public void setModifyby( Integer modifyby ) {
    this.modifyby = modifyby;
  }
  
  public Date getModifydate() {
    return modifydate;
  }
  
  public void setModifydate( Date modifydate ) {
    this.modifydate = modifydate;
  }
  
}

