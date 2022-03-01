package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Bill)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-01 15:48:56
 */
public class Bill implements Serializable {
  private static final long serialVersionUID = 925895349705147816L;
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 订单号
   */
  private String billcode;
  /**
   * 商品名
   */
  private String goodname;
  /**
   * 商品编号
   */
  private String goodcode;
  /**
   * 购买数量
   */
  private Integer quantity;
  /**
   * 商品单价
   */
  private Integer goodprice;
  /**
   * 总价
   */
  private Integer totalprice;
  /**
   * 顾客编号
   */
  private String customercode;
  /**
   * 配送地址
   */
  private String address;
  /**
   * 下单时间
   */
  private Date billtime;
  /**
   * 支付方式
   */
  private Integer paymentmethod;
  /**
   * 配送时间
   */
  private Date deliverytime;
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
  
  public String getBillcode() {
    return billcode;
  }
  
  public void setBillcode( String billcode ) {
    this.billcode = billcode;
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
  
  public Integer getQuantity() {
    return quantity;
  }
  
  public void setQuantity( Integer quantity ) {
    this.quantity = quantity;
  }
  
  public Integer getGoodprice() {
    return goodprice;
  }
  
  public void setGoodprice( Integer goodprice ) {
    this.goodprice = goodprice;
  }
  
  public Integer getTotalprice() {
    return totalprice;
  }
  
  public void setTotalprice( Integer totalprice ) {
    this.totalprice = totalprice;
  }
  
  public String getCustomercode() {
    return customercode;
  }
  
  public void setCustomercode( String customercode ) {
    this.customercode = customercode;
  }
  
  public String getAddress() {
    return address;
  }
  
  public void setAddress( String address ) {
    this.address = address;
  }
  
  public Date getBilltime() {
    return billtime;
  }
  
  public void setBilltime( Date billtime ) {
    this.billtime = billtime;
  }
  
  public Integer getPaymentmethod() {
    return paymentmethod;
  }
  
  public void setPaymentmethod( Integer paymentmethod ) {
    this.paymentmethod = paymentmethod;
  }
  
  public Date getDeliverytime() {
    return deliverytime;
  }
  
  public void setDeliverytime( Date deliverytime ) {
    this.deliverytime = deliverytime;
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

