package com.chaffee.entity;

import java.util.Date;

/**
 * (Good)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-02 16:29:22
 */
public class Good {
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 商品名
   */
  private String goodCame;
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
  private Integer owner;
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
  
  public String getGoodCame() {
    return goodCame;
  }
  
  public void setGoodCame( String goodCame ) {
    this.goodCame = goodCame;
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
  
  public Integer getOwner() {
    return owner;
  }
  
  public void setOwner( Integer owner ) {
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
        ", goodCame='" + goodCame + '\'' +
        ", goodCode='" + goodCode + '\'' +
        ", goodType=" + goodType +
        ", inventory=" + inventory +
        ", owner=" + owner +
        ", createdBy=" + createdBy +
        ", creationDate=" + creationDate +
        ", modifyBy=" + modifyBy +
        ", modifyDate=" + modifyDate +
        '}';
  }
}

