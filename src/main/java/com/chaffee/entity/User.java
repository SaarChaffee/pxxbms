package com.chaffee.entity;

import java.util.Calendar;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-02 16:29:23
 */
public class User{
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
  private String userName;
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
  /**
   * 角色名
   */
  private String userRoleName;
  /**
   * 年龄
   */
  private int age;
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
  
  public String getUserCode() {
    return userCode;
  }
  
  public void setUserCode( String userCode ) {
    this.userCode = userCode;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName( String userName ) {
    this.userName = userName;
  }
  
  public String getUserPassword() {
    return userPassword;
  }
  
  public void setUserPassword( String userPassword ) {
    this.userPassword = userPassword;
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
    setAge();
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
  
  public Integer getUserRole() {
    return userRole;
  }
  
  public void setUserRole( Integer userRole ) {
    this.userRole = userRole;
  }
  
  public Integer getCreatedBy() {
    return createdBy;
  }
  
  public void setCreatedBy( Integer createdBy ) {
    this.createdBy = createdBy;
  }
  
  public Date getCreationDate() {
    return creationDate;
  }
  
  public void setCreationDate( Date creationDate ) {
    this.creationDate = creationDate;
  }
  
  public Integer getModifyBy() {
    return modifyBy;
  }
  
  public void setModifyBy( Integer modifyBy ) {
    this.modifyBy = modifyBy;
  }
  
  public Date getModifyDate() {
    return modifyDate;
  }
  
  public void setModifyDate( Date modifyDate ) {
    this.modifyDate = modifyDate;
  }
  
  public String getUserRoleName() {
    return userRoleName;
  }
  
  public void setUserRoleName( String userRoleName ) {
    this.userRoleName = userRoleName;
  }
  
  public int getAge() {
    return age;
  }
  
  public void setAge() {
    Calendar now = Calendar.getInstance();
    Calendar bir = Calendar.getInstance();
    now.setTime( new Date( System.currentTimeMillis() ) );
    bir.setTime( birthday );
//    System.out.println( now.getTime() );
//    System.out.println( bir.getTime() );
//    System.out.println( now.get( Calendar.YEAR ) - bir.get( Calendar.YEAR ) );
    this.age = now.get( Calendar.YEAR ) - bir.get( Calendar.YEAR );
  }
  
  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", userCode='" + userCode + '\'' +
        ", userName='" + userName + '\'' +
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
        ", userRoleName='" + userRoleName + '\'' +
        ", age=" + age +
        '}';
  }
}

