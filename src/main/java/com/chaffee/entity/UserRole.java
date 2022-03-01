package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;


public class UserRole implements Serializable {
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 角色编码
   */
  private String roleCode;
  /**
   * 角色名称
   */
  private String roleName;
  /**
   * 创建者
   */
  private Integer createdBy;
  /**
   * 创建时间
   */
  private Date creationDate;
  /**
   * 修改者
   */
  private Integer modifyBy;
  /**
   * 修改时间
   */
  private Date modifyDate;
  
  public UserRole() {
  }
  
  public UserRole( Integer id, String roleCode, String roleName, Integer createdBy, Date creationDate,
                   Integer modifyBy, Date modifyDate ) {
    this.id = id;
    this.roleCode = roleCode;
    this.roleName = roleName;
    this.createdBy = createdBy;
    this.creationDate = creationDate;
    this.modifyBy = modifyBy;
    this.modifyDate = modifyDate;
  }
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
  
  public String getRoleCode() {
    return roleCode;
  }
  
  public void setRoleCode( String roleCode ) {
    this.roleCode = roleCode;
  }
  
  public String getRoleName() {
    return roleName;
  }
  
  public void setRoleName( String roleName ) {
    this.roleName = roleName;
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
  
  @Override
  public String toString() {
    return "UserRole{" +
        "id=" + id +
        ", roleCode='" + roleCode + '\'' +
        ", roleName='" + roleName + '\'' +
        ", createdBy=" + createdBy +
        ", creationDate=" + creationDate +
        ", modifyBy=" + modifyBy +
        ", modifyDate=" + modifyDate +
        '}';
  }
}

