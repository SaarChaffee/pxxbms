package com.chaffee.entity;

import java.util.Date;

/**
 * (GoodType)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-02 16:29:23
 */
public class GoodType{
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 商品类别编号
   */
  private Integer typecode;
  /**
   * 商品类别名称
   */
  private String typename;
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

