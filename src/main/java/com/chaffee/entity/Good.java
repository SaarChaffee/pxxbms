package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;


public class Good implements Serializable {
  
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 商品名
   */
  private String goodName;
  /**
   * 商品编号
   */
  private String goodCode;
  /**
   * 商品类别
   */
  private Integer goodType;
  /**
   * 商品库存
   */
  private Integer inventory;
  /**
   * 商品拥有者(取自用户表)
   */
  private String owner;
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
  
  public Good() {
  }
  
  public Good( Integer id, String goodName, String goodCode, Integer goodType, Integer inventory, String owner,
               Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate ) {
    this.id = id;
    this.goodName = goodName;
    this.goodCode = goodCode;
    this.goodType = goodType;
    this.inventory = inventory;
    this.owner = owner;
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
  
  public String getGoodName() {
    return goodName;
  }
  
  public void setGoodName( String goodName ) {
    this.goodName = goodName;
  }
  
  public String getGoodCode() {
    return goodCode;
  }
  
  public void setGoodCode( String goodCode ) {
    this.goodCode = goodCode;
  }
  
  public Integer getGoodType() {
    return goodType;
  }
  
  public void setGoodType( Integer goodType ) {
    this.goodType = goodType;
  }
  
  public Integer getInventory() {
    return inventory;
  }
  
  public void setInventory( Integer inventory ) {
    this.inventory = inventory;
  }
  
  public String getOwner() {
    return owner;
  }
  
  public void setOwner( String owner ) {
    this.owner = owner;
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
    return "Good{" +
        "id=" + id +
        ", goodName='" + goodName + '\'' +
        ", goodCode='" + goodCode + '\'' +
        ", goodType=" + goodType +
        ", inventory=" + inventory +
        ", owner='" + owner + '\'' +
        ", createdBy=" + createdBy +
        ", creationDate=" + creationDate +
        ", modifyBy=" + modifyBy +
        ", modifyDate=" + modifyDate +
        '}';
  }
}

