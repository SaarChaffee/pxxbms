package com.chaffee.entity;

import java.util.Date;

/**
 * (Bill)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-02 16:29:22
 */
public class Bill {
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 订单号
   */
  private String billCode;
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
  
  /**
   * 顾客姓名
   */
  private String customerName;
  /**
   * 商品名
   */
  private String goodName;
  /**
   * 支付方式
   */
  private String paymentMethodName;
  
  
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
  
  public String getCustomerName() {
    return customerName;
  }
  
  public void setCustomerName( String customerName ) {
    this.customerName = customerName;
  }
  
  public String getGoodName() {
    return goodName;
  }
  
  public void setGoodName( String goodName ) {
    this.goodName = goodName;
  }
  
  public String getPaymentMethodName() {
    return paymentMethodName;
  }
  
  public void setPaymentMethodName( String paymentMethodName ) {
    this.paymentMethodName = paymentMethodName;
  }
  
  @Override
  public String toString() {
    return "Bill{" +
        "id=" + id +
        ", billCode='" + billCode + '\'' +
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
        ", customerName='" + customerName + '\'' +
        ", goodName='" + goodName + '\'' +
        ", paymentMethodName='" + paymentMethodName + '\'' +
        '}';
  }
}

