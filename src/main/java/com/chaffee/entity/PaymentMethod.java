package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;

public class PaymentMethod implements Serializable {
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 支付方式编号
   */
  private String typeCode;
  /**
   * 支付方式名称
   */
  private String typeName;
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
  
  public PaymentMethod() {
  }
  
  public PaymentMethod( Integer id, String typeCode, String typeName, Integer createdBy, Date creationDate,
                        Integer modifyBy, Date modifyDate ) {
    this.id = id;
    this.typeCode = typeCode;
    this.typeName = typeName;
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
  
  public String getTypeCode() {
    return typeCode;
  }
  
  public void setTypeCode( String typeCode ) {
    this.typeCode = typeCode;
  }
  
  public String getTypeName() {
    return typeName;
  }
  
  public void setTypeName( String typeName ) {
    this.typeName = typeName;
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
    return "PaymentMethod{" +
        "id=" + id +
        ", typeCode='" + typeCode + '\'' +
        ", typeName='" + typeName + '\'' +
        ", createdBy=" + createdBy +
        ", creationDate=" + creationDate +
        ", modifyBy=" + modifyBy +
        ", modifyDate=" + modifyDate +
        '}';
  }
}

