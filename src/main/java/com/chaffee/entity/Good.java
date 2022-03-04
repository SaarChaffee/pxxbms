package com.chaffee.entity;

import java.util.Date;

/**
 * (Good)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-02 16:29:22
 */
public class Good{
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
  private Integer owner;
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

