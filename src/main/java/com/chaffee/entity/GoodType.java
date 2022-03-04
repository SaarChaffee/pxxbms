package com.chaffee.entity;

import java.util.Date;

/**
 * (GoodType)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-02 16:29:23
 */
public class GoodType {
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 商品类别编号
   */
  private Integer typeCode;
  /**
   * 商品类别名称
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
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
  
  public Integer getTypeCode() {
    return typeCode;
  }
  
  public void setTypeCode( Integer typeCode ) {
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
    return "GoodType{" +
        "id=" + id +
        ", typeCode=" + typeCode +
        ", typeName='" + typeName + '\'' +
        ", createdBy=" + createdBy +
        ", creationDate=" + creationDate +
        ", modifyBy=" + modifyBy +
        ", modifyDate=" + modifyDate +
        '}';
  }
}

