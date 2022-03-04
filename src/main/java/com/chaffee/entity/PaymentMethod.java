package com.chaffee.entity;

import java.util.Date;

/**
 * (PaymentMethod)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-02 16:29:23
 */
public class PaymentMethod{
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 支付方式编号
   */
  private Integer typeCode;
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
  
  
}

