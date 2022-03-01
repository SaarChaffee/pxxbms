package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (PaymentMethod)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-01 15:48:57
 */
public class PaymentMethod implements Serializable {
  private static final long serialVersionUID = -62274207508044491L;
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 支付方式编号
   */
  private String typecode;
  /**
   * 支付方式名称
   */
  private String typename;
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
  
  public String getTypecode() {
    return typecode;
  }
  
  public void setTypecode( String typecode ) {
    this.typecode = typecode;
  }
  
  public String getTypename() {
    return typename;
  }
  
  public void setTypename( String typename ) {
    this.typename = typename;
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

