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
  
  
}

