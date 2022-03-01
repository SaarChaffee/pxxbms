package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;


public class Bill implements Serializable {
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 订单号
   */
  private String billCode;
  /**
   * 商品名
   */
  private String goodName;
  /**
   * 商品编号
   */
  private String goodCode;
  /**
   * 购买数量
   */
  private Integer quantity;
  /**
   * 商品单价
   */
  private Integer goodPrice;
  /**
   * 总价
   */
  private Integer totalPrice;
  /**
   * 顾客编号
   */
  private String customerCode;
  /**
   * 配送地址
   */
  private String address;
  /**
   * 下单时间
   */
  private Date billTime;
  /**
   * 支付方式
   */
  private Integer paymentMethod;
  /**
   * 配送时间
   */
  private Date deliveryTime;
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
  
  public Bill() {
  }
  
  public Bill( Integer id, String billCode, String goodName, String goodCode, Integer quantity, Integer goodPrice,
               Integer totalPrice, String customerCode, String address, Date billTime, Integer paymentMethod,
               Date deliveryTime, Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate ) {
    this.id = id;
    this.billCode = billCode;
    this.goodName = goodName;
    this.goodCode = goodCode;
    this.quantity = quantity;
    this.goodPrice = goodPrice;
    this.totalPrice = totalPrice;
    this.customerCode = customerCode;
    this.address = address;
    this.billTime = billTime;
    this.paymentMethod = paymentMethod;
    this.deliveryTime = deliveryTime;
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
  
  public String getBillCode() {
    return billCode;
  }
  
  public void setBillCode( String billCode ) {
    this.billCode = billCode;
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
  
  public Integer getQuantity() {
    return quantity;
  }
  
  public void setQuantity( Integer quantity ) {
    this.quantity = quantity;
  }
  
  public Integer getGoodPrice() {
    return goodPrice;
  }
  
  public void setGoodPrice( Integer goodPrice ) {
    this.goodPrice = goodPrice;
  }
  
  public Integer getTotalPrice() {
    return totalPrice;
  }
  
  public void setTotalPrice( Integer totalPrice ) {
    this.totalPrice = totalPrice;
  }
  
  public String getCustomerCode() {
    return customerCode;
  }
  
  public void setCustomerCode( String customerCode ) {
    this.customerCode = customerCode;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress( String address ) {
    this.address = address;
  }
  
  public Date getBillTime() {
    return billTime;
  }
  
  public void setBillTime( Date billTime ) {
    this.billTime = billTime;
  }
  
  public Integer getPaymentMethod() {
    return paymentMethod;
  }
  
  public void setPaymentMethod( Integer paymentMethod ) {
    this.paymentMethod = paymentMethod;
  }
  
  public Date getDeliveryTime() {
    return deliveryTime;
  }
  
  public void setDeliveryTime( Date deliveryTime ) {
    this.deliveryTime = deliveryTime;
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
    return "Bill{" +
        "id=" + id +
        ", billCode='" + billCode + '\'' +
        ", goodName='" + goodName + '\'' +
        ", goodCode='" + goodCode + '\'' +
        ", quantity=" + quantity +
        ", goodPrice=" + goodPrice +
        ", totalPrice=" + totalPrice +
        ", customerCode='" + customerCode + '\'' +
        ", address='" + address + '\'' +
        ", billTime=" + billTime +
        ", paymentMethod=" + paymentMethod +
        ", deliveryTime=" + deliveryTime +
        ", createdBy=" + createdBy +
        ", creationDate=" + creationDate +
        ", modifyBy=" + modifyBy +
        ", modifyDate=" + modifyDate +
        '}';
  }
}

