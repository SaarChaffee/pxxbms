package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UserRole)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-01 15:48:57
 */
public class UserRole implements Serializable {
  private static final long serialVersionUID = 439607195758693454L;
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 角色编码
   */
  private String rolecode;
  /**
   * 角色名称
   */
  private String rolename;
  /**
   * 创建者
   */
  private Integer createdby;
  /**
   * 创建时间
   */
  private Date creationdate;
  /**
   * 修改者
   */
  private Integer modifyby;
  /**
   * 修改时间
   */
  private Date modifydate;
  
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
  
  public String getRolecode() {
    return rolecode;
  }
  
  public void setRolecode( String rolecode ) {
    this.rolecode = rolecode;
  }
  
  public String getRolename() {
    return rolename;
  }
  
  public void setRolename( String rolename ) {
    this.rolename = rolename;
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

