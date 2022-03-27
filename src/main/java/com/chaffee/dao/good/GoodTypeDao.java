/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.good;

import com.chaffee.entity.GoodType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GoodTypeDao {
  /**
   * 获得商品类型列表
   *
   * @param connection
   * @return List<GoodType>
   * @throws SQLException
   */
  public List<GoodType> getGoodTypeList( Connection connection ) throws SQLException;
  
  /**
   * 通过id获得商品类型
   *
   * @param connection
   * @param id
   * @return GoodType
   * @throws SQLException
   */
  public GoodType getGoodTypeById( Connection connection, int id ) throws SQLException;
  
  /**
   * 通过typeCode
   *
   * @param connection
   * @param typeCode
   * @return GoodType
   * @throws SQLException
   */
  public GoodType getGoodTypeByCode( Connection connection, int typeCode ) throws SQLException;
  
  /**
   * 增加商品类型
   *
   * @param connection
   * @param goodType
   * @return
   * @throws SQLException
   */
  public int addGoodType( Connection connection, GoodType goodType ) throws SQLException;
  
  /**
   * 删除商品类型
   *
   * @param connection
   * @param id
   * @return
   * @throws SQLException
   */
  public int delGoodType( Connection connection, int id ) throws SQLException;
  
  /**
   * 修改商品类型
   *
   * @param connection
   * @param goodType
   * @return
   * @throws SQLException
   */
  public int updGoodType( Connection connection, GoodType goodType ) throws SQLException;
}
