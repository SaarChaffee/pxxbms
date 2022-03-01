package com.chaffee.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (GoodType)实体类
 *
 * @author SaarChaffee
 * @since 2022-03-01 15:48:56
 */
public class GoodType implements Serializable {
  private static final long serialVersionUID = -90304299374502332L;
  /**
   * 主键ID
   */
  private Integer id;
  /**
   * 商品类别编号
   */
  private String typecode;
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
  
  
  public Integer getId() {
    return id;
  }
  
  public void setId( Integer id ) {
    this.id = id;
  }
  
  public String getTypecode() {
    return typecode;
  }
  
  public void setTypecode( String typecode ) {
    this.typecode = typecode;
  }
  
  public String getTypename() {
    return typename;
  }
  
  public void setTypename( String typename ) {
    this.typename = typename;
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

