package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Good)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-01 15:48:56
 */
public class Good implements Serializable {
  private static final long serialVersionUID = 802854959466692710L;
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 商品名
   */
  private String goodname;
  /**
   * 商品编号
   */
  private String goodcode;
  /**
   * 商品类别
   */
  private Integer goodtype;
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
  
  public String getGoodname() {
    return goodname;
  }
  
  public void setGoodname( String goodname ) {
    this.goodname = goodname;
  }
  
  public String getGoodcode() {
    return goodcode;
  }
  
  public void setGoodcode( String goodcode ) {
    this.goodcode = goodcode;
  }
  
  public Integer getGoodtype() {
    return goodtype;
  }
  
  public void setGoodtype( Integer goodtype ) {
    this.goodtype = goodtype;
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

